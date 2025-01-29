package com.uz.justplan.services;

import com.uz.justplan.beans.AddCompanyReq;
import com.uz.justplan.beans.AddResourceReq;
import com.uz.justplan.beans.CommonResp;
import com.uz.justplan.core.Company;
import com.uz.justplan.core.CompanyRepository;
import com.uz.justplan.lookup.RoleEnum;
import com.uz.justplan.resources.*;
import com.uz.justplan.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyDashboardService {
    private CompanyRepository compRepo;
    private ResourceRepository resourceRepo;
    private ResourceRoleRepository resourceRoleRepo;
    private RoleRepository roleRepo;
    private CompanyWeekendRepository companyWeekendRepository;
    private CompanyWorkingHourRepository companyWorkingHourRepository;

    @Autowired
    public CompanyDashboardService(CompanyRepository compRepo,
                                   ResourceRepository resourceRepo,
                                   ResourceRoleRepository resourceRoleRepo,
                                   RoleRepository roleRepo,
                                   CompanyWeekendRepository companyWeekendRepository,
                                   CompanyWorkingHourRepository companyWorkingHourRepository) {
        this.compRepo = compRepo;
        this.resourceRepo = resourceRepo;
        this.resourceRoleRepo = resourceRoleRepo;
        this.roleRepo = roleRepo;
        this.companyWeekendRepository = companyWeekendRepository;
        this.companyWorkingHourRepository = companyWorkingHourRepository;
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
        final Resource resource = new Resource();
        resource.setCompanyId(comp.getId());
        resource.setEmail(req.getEmail());
        resource.setActive(true);
        resource.setIndividualCapacity(true);
        resource.setDesignation(req.getDesignation());
        resource.setFirstName(req.getFirstName());
        resource.setSecondName(req.getSecondName());
        resource.setLastName(req.getLastName());
        resourceRepo.save(resource);

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
                        ResourceRole rr = new ResourceRole();
                        rr.setResourceId(resource.getId());
                        rr.setActive(true);
                        rr.setParticipationPercentTime(100);
                        rr.setRoleId(newRole.getId());
                        resourceRoleRepo.save(rr);
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

    public List<CommonResp> addResource(final AddResourceReq req) {
        List<CommonResp> list = Arrays.asList();
        //.filter(Objects::nonNull)  // Exclude nulls
        String[] emails = req.getEmail().split("[,;\\s\\t\\n\\r]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        //[,;\t\n\r]+
        Map<String, Resource> existingResources = resourceRepo.findByCompanyId(req.getCompanyId())
                .stream().collect(Collectors.toMap(resource -> resource.getEmail().toLowerCase(), resource -> resource));
        for (String email : emails) {
            CommonResp resp = new CommonResp();
            resp.setContext(email);
            if (Validation.isValidEmail(email)) {
                Resource existingResource = existingResources.get(email.toLowerCase());
                long resourceId = existingResource != null ? existingResource.getId() : 0L;
                Optional<ResourceRole> existingResourceRole = Optional.empty();
                if (existingResource == null) {
                    final Resource resource = new Resource();
                    resource.setCompanyId(req.getCompanyId());
                    resource.setEmail(email);
                    resource.setActive(true);
                    resource.setIndividualCapacity(true);
                    resourceRepo.save(resource);
                    resourceId = resource.getId();
                } else {
                    existingResourceRole = resourceRoleRepo.findByResourceIdAndRoleId(resourceId, req.getRoleId());
                }
                if (existingResourceRole.isEmpty()) {
                    ResourceRole rr = new ResourceRole();
                    rr.setResourceId(resourceId);
                    rr.setActive(true);
                    rr.setParticipationPercentTime(100);
                    rr.setRoleId(req.getRoleId());
                    resourceRoleRepo.save(rr);
                    if (existingResource == null) {
                        resp.setMessage("Resource is added with role.");
                    } else {
                        resp.setMessage("New role is added for existing resource.");
                    }
                } else {
                    resp.setMessage("Resource is already there with role.");
                }

            } else {
                resp.setMessage("Invalid email address");
            }
            list.add(resp);
        }
        return list;
    }
}
