package com.uz.justplan.services;

import com.uz.justplan.beans.*;
import com.uz.justplan.core.*;
import com.uz.justplan.lookup.Priority;
import com.uz.justplan.lookup.PriorityRepository;
import com.uz.justplan.lookup.RoleEnum;
import com.uz.justplan.plan.Epic;
import com.uz.justplan.plan.EpicRepository;
import com.uz.justplan.resources.*;
import com.uz.justplan.utils.Utils;
import com.uz.justplan.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public CompanyDashboardService(CompanyRepository compRepo,
                                   ResourceRepository resourceRepo,
                                   ResourceRoleRepository resourceRoleRepo,
                                   RoleRepository roleRepo,
                                   CompanyWeekendRepository companyWeekendRepository,
                                   CompanyWorkingHourRepository companyWorkingHourRepository,
                                   ProductRepository productRepo,
                                   ComponentRepository componentRepo,
                                   PriorityRepository priorityRepo,
                                   EpicRepository epicRepo,
                                   DesignationRepository designRepo,
                                   ProductResourceRepository prodResourceRepo) {
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
    }

    public CommonResp addCompany(final AddCompanyReq req) {
        final CommonResp resp = new CommonResp();
        final Company comp = new Company();
        comp.setSample(false);
        comp.setActive(true);
        comp.setName(req.getName());
        comp.setCountryId(req.getCountryId());
        compRepo.save(comp);
        resp.setId(comp.getId());
        Long resourceId = createNewResource(req.getEmail(), req.getResourceName(), req.getDesignation(), comp);
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
        resp.setMessage("Company is added.");
        return resp;
    }

    public CommonResp addProduct(final AddProductReq req) {
        CommonResp resp = new CommonResp();
        final Product model = new Product();
        model.setActive(true);
        model.setName(req.getName());
        model.setCompanyId(req.getCompanyId());
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
        assignNewProductResourceIfNotExist(model.getProductManagerId(), model.getId());
        assignNewProductResourceIfNotExist(model.getProductOwnerId(), model.getId());
        resp.setMessage("Product is added.");
        resp.setId(model.getId());
        return resp;
    }

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
        reqs.stream().filter(req -> !Validation.isEmpty(req.getPriority()))
                .map(req -> req.getPriority().trim()).distinct().forEach(pr -> {
                    priorities.put(pr.toLowerCase(), findOrCreateNewPriority(pr, product.getCompanyId()));
                });
        for (AddEpicReq req : reqs) {
            responseList.add(findOrCreateNewEpic(req, components, priorities));
        }
        return responseList;
    }

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

    public CommonResp mapDesignation(MapDesignationReq req) {
        CommonResp resp = new CommonResp();
        Designation designation = designRepo.findById(req.getDesignationId()).get();
        designation.setRoleId(req.getRoleId());
        designRepo.save(designation);
        resp.setMessage("Designation updated.");
        resourceRepo.findByDesignationId(req.getDesignationId()).forEach(
                r -> {
                    assignNewResourceRoleIfNotExist(
                            r.getId(), req.getRoleId());
                }
        );
        return resp;
    }

    private CommonResp findOrCreateNewResource(AddResourceReq req, Map<String, Designation> designations) {
        CommonResp resp = new CommonResp();
        if (!Validation.isValidEmail(req.getEmail())) {
            resp.setContext(req.getEmail());
            resp.setMessage("Invalid email: " + req.getEmail());
            return resp;
        }
        Optional<Resource> modelO = resourceRepo.findByCompanyIdAndEmailIgnoreCase(req.getCompanyId(),
                req.getEmail().trim());
        Resource model = null;
        if (!modelO.isEmpty()) {
            model = modelO.get();
            resp.setMessage("Updated");
        } else {
            model = new Resource();
            model.setEmail(req.getEmail().trim());
            model.setActive(true);
            model.setCompanyId(req.getCompanyId());
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
        if (design != null) {
            assignNewResourceRoleIfNotExist(model.getId(), design.getRoleId());
        }
        if (req.getProductId() != null) {
            assignNewProductResourceIfNotExist(model.getId(), req.getProductId());
        }
        resp.setId(model.getId());
        return resp;
    }

    private CommonResp findOrCreateNewEpic(AddEpicReq req, Map<String, Long> components, Map<String, Long> priorities) {
        CommonResp resp = new CommonResp();
        Optional<Epic> modelO = epicRepo.findByProductIdAndDescriptionIgnoreCase(req.getProductId(),
                req.getDescription().trim());
        Epic model = null;
        if (!modelO.isEmpty()) {
            model = modelO.get();
            resp.setMessage("Updated");
        } else {
            model = new Epic();
            model.setDescription(req.getDescription().trim());
            model.setProductId(req.getProductId());
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
        model.setPlanned(false);
        model.setCompleteByReleaseId(null);
        model.setRaisedByResourceId(null);
        model.setValueGain(Utils.getDoubleValue(req.getValueGain()));
        if (!Validation.isEmpty(req.getDateFormat())) {
            model.setRequiredBy(Utils.getLocalDateFromString(req.getRequiredBy(), req.getDateFormat()));
        }
        epicRepo.save(model);
        resp.setId(model.getId());
        return resp;
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

    private Optional<Resource> getExistingResource(Long companyId, String email) {
        return resourceRepo.findByCompanyIdAndEmailIgnoreCase(companyId, email.toLowerCase());
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
            resourceId = createNewResource(email, Utils.getNameFromEmail(email), "", company);
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
        Optional<Resource> resource = getExistingResource(company.getId(), email);
        if (!resource.isEmpty()) {
            return resource.get().getId();
        }
        return createNewResource(email, name, designation, company);
    }

    private long createNewResource(String email, String name, String designation, Company company) {
        Resource resource = new Resource();
        resource.setCompanyId(company.getId());
        resource.setEmail(email.toLowerCase());
        if (Validation.isEmpty(name)) {
            resource.setName(Utils.getNameFromEmail(email));
        } else {
            resource.setName(name);
        }
        Designation design = null;
        if (!Validation.isEmpty(designation)) {
            design = findOrCreateNewDesignation(designation, company.getId());
            resource.setDesignationId(design.getId());
        }
        resource.setCountryId(company.getCountryId());
        resource.setActive(true);
        resource.setIndividualCapacity(true);
        resourceRepo.save(resource);
        if (design != null) {
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
            resourceRole.setParticipationPercentTime(100);
            resourceRole.setRoleId(roleId);
            resourceRoleRepo.save(resourceRole);
        }
    }

    private void assignNewProductResourceIfNotExist(long resourceId, long productId) {
        List<ProductResource> existingRole = prodResourceRepo.findByResourceId(resourceId);
        if (existingRole.isEmpty() || existingRole.stream().noneMatch(
                pr -> pr.getProductId().equals(productId))) {
            ProductResource model = new ProductResource();
            model.setResourceId(resourceId);
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


}
