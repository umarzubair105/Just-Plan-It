package com.uz.justplan.services;

import com.uz.justplan.beans.*;
import com.uz.justplan.beans.response.ResourceRightBean;
import com.uz.justplan.core.*;
import com.uz.justplan.lookup.*;
import com.uz.justplan.plan.Epic;
import com.uz.justplan.plan.EpicRepository;
import com.uz.justplan.resources.*;
import com.uz.justplan.utils.SecurePasswordGenerator;
import com.uz.justplan.utils.Utils;
import com.uz.justplan.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyDashboardService {
    private CompanyRepository compRepo;
    private ResourceRepository resourceRepo;
    private ProductRepository productRepo;
    private ResourceRoleRepository resourceRoleRepo;
    private ProductResourceRepository prodResourceRepo;
    private RoleRepository roleRepo;
    private ComponentRepository componentRepo;
    private PriorityRepository priorityRepo;
    private EpicRepository epicRepo;
    private DesignationRepository designRepo;
    private CompanyWeekendRepository companyWeekendRepository;
    private CompanyWorkingHourRepository companyWorkingHourRepository;
    private CountryRepository countryRepository;

    private EmailService emailService;

    @Autowired
    public CompanyDashboardService(final CompanyRepository compRepo,
                                   final ResourceRepository resourceRepo,
                                   final ResourceRoleRepository resourceRoleRepo,
                                   final RoleRepository roleRepo,
                                   final CompanyWeekendRepository companyWeekendRepository,
                                   final CompanyWorkingHourRepository companyWorkingHourRepository,
                                   final ProductRepository productRepo,
                                   final ComponentRepository componentRepo,
                                   final PriorityRepository priorityRepo,
                                   final EpicRepository epicRepo,
                                   final DesignationRepository designRepo,
                                   final ProductResourceRepository prodResourceRepo,
                                   final CountryRepository countryRepository,
                                   final EmailService emailService) {
        this.compRepo = compRepo;
        this.resourceRepo = resourceRepo;
        this.resourceRoleRepo = resourceRoleRepo;
        this.roleRepo = roleRepo;
        this.companyWeekendRepository = companyWeekendRepository;
        this.companyWorkingHourRepository = companyWorkingHourRepository;
        this.productRepo = productRepo;
        this.componentRepo = componentRepo;
        this.priorityRepo = priorityRepo;
        this.epicRepo = epicRepo;
        this.designRepo = designRepo;
        this.prodResourceRepo = prodResourceRepo;
        this.countryRepository = countryRepository;
        this.emailService = emailService;
    }

    @Transactional
    public CommonResp addCompany(final AddCompanyReq req) {
        Assert.isTrue(
                compRepo.findByNameIgnoreCaseAndActive(req.getName(), true)
                        .isEmpty(), "Company already exists with the same name");
        Assert.isTrue(
                resourceRepo.findByEmailIgnoreCase(req.getEmail()).isEmpty(),
                "This email is already registered.");
        final CommonResp resp = new CommonResp();
        final Company comp = new Company();
        comp.setSample(false);
        comp.setActive(true);
        comp.setName(req.getName());
        comp.setCountryId(req.getCountryId());
        comp.setCode(Utils.generateCode());
        compRepo.save(comp);
        resp.setId(comp.getId());
        String password = SecurePasswordGenerator.generatePassword();
        Long resourceId = createNewResource(req.getEmail(), req.getResourceName(),
                req.getDesignation(), req.getMobileNumber(), comp, password);
        roleRepo.findByCompanyIdAndActive(req.getSampleCompanyId(), true)
                .forEach(role -> {
                    final Role newRole = new Role();
                    newRole.setName(role.getName());
                    newRole.setGroupTask(role.isGroupTask());
                    newRole.setCompanyId(comp.getId());
                    newRole.setTaskAssignable(role.isTaskAssignable());
                    newRole.setCode(role.getCode());
                    newRole.setActive(role.isActive());
                    roleRepo.save(newRole);
                    if (newRole.getCode().equals(RoleEnum.ADMIN)) {
                        assignNewResourceRoleIfNotExist(resourceId, newRole.getId());
                    }
                });

        companyWeekendRepository.findByCompanyIdAndActive(req.getSampleCompanyId(), true)
                .forEach(cw -> {
                    final CompanyWeekend newM = new CompanyWeekend();
                    newM.setDay(cw.getDay());
                    newM.setCompanyId(comp.getId());
                    newM.setActive(cw.isActive());
                    companyWeekendRepository.save(newM);
                });
        companyWorkingHourRepository.findByCompanyIdAndActive(req.getSampleCompanyId(), true)
                .forEach(cw -> {
                    try {
                        final CompanyWorkingHour newM = (CompanyWorkingHour) cw.clone();
                        newM.setCompanyId(comp.getId());
                        companyWorkingHourRepository.save(newM);
                    } catch (CloneNotSupportedException e) {
                        throw new RuntimeException(e);
                    }
                });
        //resp.setContext(comp.getCode());
        resp.setContext("password_0c" + password + "password_@c" + SecurePasswordGenerator.generatePassword());
        resp.setMessage("Company is added with code: " + comp.getCode());
        return resp;
    }

    @Transactional
    public CommonResp addProduct(final AddProductReq req) {
        System.out.println("-----req:" + req.toString() + "--------------------------------");
        CommonResp resp = new CommonResp();
        final Product model = new Product();
        model.setActive(true);
        model.setName(req.getName());
        model.setCompanyId(req.getCompanyId());
        model.setCode(req.getCode());
        model.setReleaseIteration(req.getReleaseIteration());
        model.setStartDate(req.getStartDate());
        model.setEndDate(req.getEndDate());
        model.setOtherActivitiesPercentTime(req.getOtherActivitiesPercentTime());
        Company company = compRepo.findById(req.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
        List<Role> roles = roleRepo.findByCompanyIdAndActive(req.getCompanyId(), true);
        roles.forEach(role -> {
            if (role.getCode().equals(RoleEnum.PM)) {
                model.setProductManagerId(
                        addMissingResourceWithRole(req.getEmailProductManager(), role.getId(),
                                role.getName(), company));
            } else if (role.getCode().equals(RoleEnum.PO)) {
                model.setProductOwnerId(
                        addMissingResourceWithRole(req.getEmailProductOwner(), role.getId(),
                                role.getName(), company));
            }
        });
        productRepo.save(model);
        assignNewProductResourceIfNotExist(model.getProductManagerId(),
                roles.stream().filter(role -> role.getCode().equals(RoleEnum.PM)).findFirst().get().getId(),
                model.getId());
        assignNewProductResourceIfNotExist(model.getProductOwnerId(),
                roles.stream().filter(role -> role.getCode().equals(RoleEnum.PO)).findFirst().get().getId(),
                model.getId());
        resp.setMessage("Product is added.");
        resp.setId(model.getId());
        return resp;
    }

    @Transactional
    public List<CommonResp> addEpics(final List<AddEpicReq> reqs) {
        List<CommonResp> responseList = new ArrayList<>();
        if (reqs.isEmpty()) {
            return responseList;
        }
        Product product = productRepo.findById(reqs.get(0).getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        Map<String, Long> components = new HashMap<>();
        reqs.stream().filter(req -> !Validation.isEmpty(req.getComponent()))
                .map(req -> req.getComponent().trim()).distinct().forEach(comp -> {
                    components.put(comp.toLowerCase(), findOrCreateNewComponent(comp, product.getCompanyId()));
                });
        Map<String, Long> priorities = new HashMap<>();
        System.out.println("Creating --------- new priority: " + reqs.get(0).getPriority());

        reqs.stream().filter(req -> !Validation.isEmpty(req.getPriority()))
                .map(req -> req.getPriority().trim()).distinct().forEach(pr -> {
                    priorities.put(pr.toLowerCase(), findOrCreateNewPriority(pr, product.getCompanyId()));
                });
        for (AddEpicReq req : reqs) {
            if (!Validation.isEmpty(req.getTitle())) {
                responseList.add(findOrCreateNewEpic(req, components, priorities));
            }
        }
        return responseList;
    }

    @Transactional
    public List<CommonResp> addResources(final List<AddResourceReq> reqs) {
        List<CommonResp> responseList = new ArrayList<>();
        if (reqs.isEmpty()) {
            return responseList;
        }
        Map<String, Designation> designations = new HashMap<>();
        long companyId = reqs.get(0).getCompanyId();
        reqs.stream().filter(req -> !Validation.isEmpty(req.getDesignation()))
                .map(req -> req.getDesignation().trim()).distinct().forEach(designation -> {
                    designations.put(designation.toLowerCase(), findOrCreateNewDesignation(designation, companyId));
                });
        for (AddResourceReq req : reqs) {
            responseList.add(findOrCreateNewResource(req, designations));
        }

        return responseList;
    }

    @Transactional
    public CommonResp mapDesignation(MapDesignationReq req) {
        CommonResp resp = new CommonResp();
        Designation designation = designRepo.findById(req.getDesignationId()).get();

        if (req.getRoleId() != null &&
                (designation.getRoleId() == null
                        || !designation.getRoleId().equals(req.getRoleId()))) {
            designation.setRoleId(req.getRoleId());
            designRepo.save(designation);
            resp.setMessage("Designation updated.");
            resourceRepo.findByDesignationId(req.getDesignationId()).forEach(
                    r -> {
                        assignNewResourceRoleIfNotExist(
                                r.getId(), req.getRoleId());
                    }
            );
        } else {
            resp.setMessage("Designation already having same role.");
        }

        return resp;
    }

    @Transactional
    public CommonResp addResource(final AddResourceReq req) {
        long companyId = req.getCompanyId();

        CommonResp resp = new CommonResp();
        if (!Validation.isValidEmail(req.getEmail())) {
            resp.setContext(req.getEmail());
            resp.setMessage("Invalid email: " + req.getEmail());
            return resp;
        }
        Optional<Resource> modelO = resourceRepo.findOneByEmailIgnoreCase(req.getEmail().trim());
        Resource model = null;
        String password = null;
        if (!modelO.isEmpty()) {
            model = modelO.get();
            Assert.isTrue(model.getCompanyId() != req.getCompanyId(), "User with email " + req.getEmail() + " already registered with some other company.");
            Assert.isTrue(false, "User with email " + req.getEmail() + " already registered.");
        } else {
            model = new Resource();
            model.setName(req.getName());
            model.setEmail(req.getEmail().trim());
            model.setActive(true);
            model.setCompanyId(req.getCompanyId());
            password = SecurePasswordGenerator.generatePassword();
            model.setPassword(SecurePasswordGenerator.encryptPassword(password));
            model.setStatus(ResourceStatus.ACTIVE);
            resp.setMessage("Added");
        }
        Designation designation = null;
        if (!Validation.isEmpty(req.getDesignation())) {
            designation = findOrCreateNewDesignation(req.getDesignation().trim(), companyId);
            model.setDesignationId(designation.getId());
        }
        if (!Validation.isEmpty(req.getMobileNumber())) {
            model.setMobileNumber(req.getMobileNumber());
        }
        model.setDateOfBirth(req.getDateOfBirthDate());
        resourceRepo.save(model);
        if (password != null) {
            emailService.sendEmailNewUser(model.getEmail(), model.getName(), password);
        }
        if (designation != null && designation.getRoleId() != null) {
            assignNewResourceRoleIfNotExist(model.getId(), designation.getRoleId());
            if (req.getProductId() != null) {
                assignNewProductResourceIfNotExist(model.getId(), designation.getRoleId(), req.getProductId());
            }
        }
        resp.setId(model.getId());
        return resp;
    }

    private CommonResp findOrCreateNewResource(AddResourceReq req, Map<String, Designation> designations) {
        CommonResp resp = new CommonResp();
        if (!Validation.isValidEmail(req.getEmail())) {
            resp.setContext(req.getEmail());
            resp.setMessage("Invalid email: " + req.getEmail());
            return resp;
        }
        Optional<Resource> modelO = resourceRepo.findOneByEmailIgnoreCase(req.getEmail().trim());
        Resource model = null;
        String password = null;
        if (!modelO.isEmpty()) {
            model = modelO.get();
            Assert.isTrue(model.getCompanyId() != req.getCompanyId(), "User with email " + req.getEmail() + " already registered with some other company.");
            resp.setMessage("Updated");
        } else {
            model = new Resource();
            model.setEmail(req.getEmail().trim());
            model.setActive(true);
            model.setCompanyId(req.getCompanyId());
            password = SecurePasswordGenerator.generatePassword();
            model.setPassword(SecurePasswordGenerator.encryptPassword(password));
            model.setStatus(ResourceStatus.ACTIVE);
            resp.setMessage("Added");
        }
        Designation design = null;
        if (!Validation.isEmpty(req.getDesignation())) {
            design = designations.get(req.getDesignation().trim().toLowerCase());
            model.setDesignationId(design.getId());
        }
        if (!Validation.isEmpty(req.getMobileNumber())) {
            model.setMobileNumber(req.getMobileNumber());
        }
        if (!Validation.isEmpty(req.getName())) {
            model.setName(req.getName());
        }
        if (!Validation.isEmpty(req.getDateFormat())) {
            model.setDateOfBirth(Utils.getLocalDateFromString(req.getDateOfBirth(), req.getDateFormat()));
        }
        resourceRepo.save(model);
        if (password != null) {
            emailService.sendEmailNewUser(model.getEmail(), model.getName(), password);
        }
        if (design != null && design.getRoleId() != null) {
            assignNewResourceRoleIfNotExist(model.getId(), design.getRoleId());
            if (req.getProductId() != null) {
                assignNewProductResourceIfNotExist(model.getId(), design.getRoleId(), req.getProductId());
            }
        }

        resp.setId(model.getId());
        return resp;
    }

    private CommonResp findOrCreateNewEpic(AddEpicReq req, Map<String, Long> components, Map<String, Long> priorities) {
        CommonResp resp = new CommonResp();
        Optional<Epic> modelO = epicRepo.findByProductIdAndTitleIgnoreCase(req.getProductId(),
                req.getTitle().trim());
        Epic model = null;
        if (!modelO.isEmpty()) {
            model = modelO.get();
            resp.setMessage("Updated");
        } else {
            model = new Epic();
            model.setTitle(req.getTitle().trim());
            model.setDetails(req.getDetails());
            model.setProductId(req.getProductId());
            model.setCode(getEpicCodeForNewEpic(req.getProductId()));
            resp.setMessage("Added");
        }
        if (!Validation.isEmpty(req.getComponent())) {
            model.setComponentId(components.get(req.getComponent().trim().toLowerCase()));
        }
        if (!Validation.isEmpty(req.getPriority())) {
            model.setPriorityId(priorities.get(req.getPriority().trim().toLowerCase()));
        }
        model.setComments(req.getComments());
        model.setRisks(req.getRisks());
        model.setActive(true);
        //model.setCompleteByReleaseId(null);
        model.setRaisedByResourceId(null);
        model.setValueGain(Utils.getDoubleValue(req.getValueGain()));
        if (!Validation.isEmpty(req.getDateFormat())) {
            model.setRequiredBy(Utils.getLocalDateFromString(req.getRequiredBy(), req.getDateFormat()));
        }
        epicRepo.save(model);
        resp.setId(model.getId());
        return resp;
    }

    public String getEpicCodeForNewEpic(long productId) {
        String prodCode = productRepo.findById(productId).get().getCode();
        long count = epicRepo.countByProductId(productId);
        return prodCode + "-" + (count + 1);
    }
    private long findOrCreateNewComponent(String name, long companyId) {
        Optional<Component> modelO = componentRepo.findByCompanyIdAndNameIgnoreCase(companyId, name);
        if (!modelO.isEmpty()) {
            return modelO.get().getId();
        }
        Component model = new Component();
        model.setName(name);
        model.setCompanyId(companyId);
        componentRepo.save(model);
        return model.getId();
    }

    private long findOrCreateNewPriority(String name, long companyId) {
        System.out.println("Creating new priority: " + name);
        Optional<Priority> modelO = priorityRepo.findByCompanyIdAndNameIgnoreCase(companyId, name);
        if (!modelO.isEmpty()) {
            return modelO.get().getId();
        }
        Priority model = new Priority();
        model.setName(name);
        model.setCompanyId(companyId);
        model.setPriorityLevel(1);
        model.setActive(true);
        priorityRepo.save(model);
        return model.getId();
    }

    private Designation findOrCreateNewDesignation(String name, long companyId) {
        Optional<Designation> modelO = designRepo.findByCompanyIdAndNameIgnoreCase(companyId, name);
        if (!modelO.isEmpty()) {
            return modelO.get();
        }
        Designation model = new Designation();
        model.setName(name);
        model.setCompanyId(companyId);
        model.setActive(true);
        designRepo.save(model);
        return model;
    }

    @Transactional
    public List<CommonResp> addResourceSimple(final AddResourceReq req) {
        List<CommonResp> responseList = new ArrayList<>();

        // Parse email list
        String[] emails = parseEmails(req.getEmail());

        // Fetch existing resources and company details
        Map<String, Resource> existingResources = getExistingResources(req.getCompanyId());
        Company company = compRepo.findById(req.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));

        for (String email : emails) {
            CommonResp response = processEmail(null, email, existingResources, company);
            responseList.add(response);
        }
        return responseList;
    }

    // ✅ Extracted: Email Parsing
    private String[] parseEmails(String emailString) {
        return emailString.split("[,;\\s\\t\\n\\r]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    // ✅ Extracted: Fetch Existing Resources
    private Map<String, Resource> getExistingResources(Long companyId) {
        return resourceRepo.findByCompanyId(companyId)
                .stream()
                .collect(Collectors.toMap(resource -> resource.getEmail().toLowerCase(), resource -> resource));
    }

    private Optional<Resource> getExistingResource(String email) {
        return resourceRepo.findOneByEmailIgnoreCase(email.toLowerCase());
    }

    // ✅ Extracted: Processing Each Email
    private CommonResp processEmail(Long roleId, String email, Map<String, Resource> existingResources, Company company) {
        CommonResp response = new CommonResp();
        response.setContext(email);

        if (!Validation.isValidEmail(email)) {
            response.setMessage("Invalid email address");
            return response;
        }

        Resource existingResource = existingResources.get(email.toLowerCase());
        long resourceId = (existingResource != null) ? existingResource.getId() : 0L;

        if (existingResource == null) {
            Optional<Resource> resource = getExistingResource(email);
            if (!resource.isEmpty()) {
                response.setMessage("User with email " + email + " is already registered.");
                return response;
            }
            resourceId = createNewResource(email, Utils.getNameFromEmail(email), "",
                    "", company, SecurePasswordGenerator.generatePassword());
            response.setId(resourceId);
            response.setMessage("Resource is added with role.");
        } else {
            response.setId(resourceId);
            if (roleId != null) {
                Optional<ResourceRole> existingRole = resourceRoleRepo.findByResourceIdAndRoleId(resourceId, roleId);

                if (existingRole.isEmpty()) {
                    assignNewResourceRoleIfNotExist(resourceId, roleId);
                    response.setMessage("New role is added for existing resource.");
                } else {
                    response.setMessage("Resource is already there with role.");
                }
            }
        }

        return response;
    }

    // ✅ Extracted: Create New Resource
    private Long addMissingResourceWithRole(String email, Long roleId, String designation, Company company) {
        Long resourceId = findOrCreateNewResource(email,
                "", designation, company);
        assignNewResourceRoleIfNotExist(resourceId, roleId);
        return resourceId;
    }

    private long findOrCreateNewResource(String email, String name, String designation, Company company) {
        Optional<Resource> resource = getExistingResource(email);
        if (!resource.isEmpty()) {
            Assert.isTrue(resource.get().getCompanyId() != company.getId(), "User with email " + email + " already registered with some other company.");
            return resource.get().getId();
        }
        return createNewResource(email, name, designation,
                "", company, SecurePasswordGenerator.generatePassword());
    }

    private long createNewResource(String email, String name, String designation,
                                   String mobileNumber,
                                   Company company, final String password) {
        Resource resource = new Resource();
        resource.setCompanyId(company.getId());
        resource.setEmail(email.toLowerCase());
        resource.setMobileNumber(mobileNumber);
        if (Validation.isEmpty(name)) {
            resource.setName(Utils.getNameFromEmail(email));
        } else {
            resource.setName(name);
        }
        resource.setPassword(SecurePasswordGenerator.encryptPassword(password));
        Designation design = null;
        if (!Validation.isEmpty(designation)) {
            design = findOrCreateNewDesignation(designation, company.getId());
            resource.setDesignationId(design.getId());
        }
        resource.setStatus(ResourceStatus.ACTIVE);
        resource.setCountryId(company.getCountryId());
        resource.setActive(true);
        resource.setIndividualCapacity(true);
        resourceRepo.save(resource);
        emailService.sendEmailNewUser(resource.getEmail(), resource.getName(), password);

        if (design != null && design.getRoleId() != null) {
            assignNewResourceRoleIfNotExist(resource.getId(), design.getRoleId());
        }
        return resource.getId();
    }

    // ✅ Extracted: Assign New Role to Resource
    private void assignNewResourceRoleIfNotExist(long resourceId, long roleId) {
        Optional<ResourceRole> existingRole = resourceRoleRepo.findByResourceIdAndRoleId(resourceId, roleId);
        if (existingRole.isEmpty()) {
            ResourceRole resourceRole = new ResourceRole();
            resourceRole.setResourceId(resourceId);
            resourceRole.setActive(true);
            resourceRole.setRoleId(roleId);
            resourceRoleRepo.save(resourceRole);
        }
    }

    private void assignNewProductResourceIfNotExist(long resourceId, long roleId, long productId) {
        List<ProductResource> existingRole = prodResourceRepo.findByResourceId(resourceId);
        if (existingRole.isEmpty() || existingRole.stream().noneMatch(
                pr -> pr.getProductId().equals(productId) && pr.getRoleId().equals(roleId))) {
            ProductResource model = new ProductResource();
            model.setResourceId(resourceId);
            model.setRoleId(roleId);
            model.setActive(true);
            if (existingRole.isEmpty()) {
                model.setParticipationPercentTime(100);
            } else {
                model.setParticipationPercentTime(0);
            }
            model.setProductId(productId);
            prodResourceRepo.save(model);
        }
    }

    public Map<String, Object> getMetadata() {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("countries", countryRepository.findAllByActiveOrderByName(true));
        metadata.put("sampleCompanies", compRepo.findAllBySampleOrderByName(true));
        return metadata;
    }

    public LoggedInDetails getLoggedInDetails(long resourceId) {
        Resource resource = resourceRepo.findById(resourceId).orElseThrow(() -> new RuntimeException("Resource not found"));
        LoggedInDetails details = new LoggedInDetails();
        details.setCompany(compRepo.findProjectionById(resource.getCompanyId()));
        return details;
    }

    @Transactional
    public CommonResp resetPassword(final String username) {
        Assert.isTrue(!Validation.isEmpty(username), "Email is not provided.");
        Optional<Resource> res = resourceRepo.findOneByEmailIgnoreCase(username);
        Assert.isTrue(res.isPresent(), "There is no user registered against " + username + ".");
        Assert.isTrue(res.get().getStatus().equals(ResourceStatus.ACTIVE),
                "User is not active against " + username + ".");
        Assert.isTrue(res.get().isActive(),
                "User is not active against " + username + ".");
        String password = SecurePasswordGenerator.generatePassword();
        res.get().setPassword(SecurePasswordGenerator.encryptPassword(password));
        resourceRepo.save(res.get());
        emailService.sendEmailPasswordReset(res.get().getEmail(), res.get().getName(), password);
        CommonResp resp = new CommonResp();
        resp.setContext(username);
        resp.setMessage("New password is sent through email.");
        return resp;
    }

    public ResourceRightBean getResourceRights(long resourceId, Long productId) {
        List<ProductResource> prs = prodResourceRepo.findByResourceIdAndActiveIsTrue(resourceId);
        ResourceRightBean bean = new ResourceRightBean();
        bean.setProducts(
                prs.stream().filter(pr -> pr.getParticipationPercentTime() > 0)
                        .map(pr -> productRepo.findById(pr.getProductId()).get())
                        .collect(Collectors.toList()));

        bean.setTeamResourceIds(
                resourceRepo.findByLeadResourceIdAndActiveIsTrue(resourceId).stream().map(r -> r.getId())
                        .collect(Collectors.toList()));

        Resource r = resourceRepo.findById(resourceId).get();
        bean.setLeadId(r.getLeadResourceId());
        resourceRoleRepo.findByResourceIdAndActiveIsTrue(resourceId).forEach(rr -> {
            Role role = roleRepo.findById(rr.getRoleId()).get();
            if (role.getCode() == RoleEnum.PM || role.getCode() == RoleEnum.PO || role.getCode() == RoleEnum.SM) {
                bean.setGlobalManager(true);
            }
            if (role.getCode() == RoleEnum.ADMIN) {
                bean.setGlobalAdmin(true);
            }
            if (role.getCode() == RoleEnum.HR) {
                bean.setGlobalHr(true);
            }
        });
        if (productId != null) {
            prs.stream().filter(pr -> pr.getParticipationPercentTime() > 0 && pr.getProductId().equals(productId)).forEach(pr -> {
                Role role = roleRepo.findById(pr.getRoleId()).get();
                if (role.getCode() == RoleEnum.PM || role.getCode() == RoleEnum.PO || role.getCode() == RoleEnum.SM) {
                    bean.setProductManager(true);
                }
                if (role.getCode() == RoleEnum.ADMIN) {
                    bean.setProductAdmin(true);
                }
                if (role.getCode() == RoleEnum.HR) {
                    bean.setProductHr(true);
                }
            });
        }
        return bean;
    }
}
