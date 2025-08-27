package com.uz.justplan.ai;

import com.uz.justplan.core.CompanyRepository;
import com.uz.justplan.core.ComponentRepository;
import com.uz.justplan.core.ProductRepository;
import com.uz.justplan.lookup.PriorityRepository;
import com.uz.justplan.lookup.ReleaseStatusEnum;
import com.uz.justplan.lookup.RoleEnum;
import com.uz.justplan.plan.*;
import com.uz.justplan.resources.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.uz.justplan.ai.AIMessageType.*;
import static com.uz.justplan.ai.AIRoute.*;

@Service
public class AIService {
    private static final Log log = LogFactory.getLog(AIService.class);
    @Autowired
    ReleaseResourceRepository releaseResourceRepository;
    @Autowired
    private CompanyRepository compRepo;
    @Autowired
    private ResourceRepository resourceRepo;
    @Autowired
    private ProductRepository productRepo;
    //@Autowired
    //private ResourceRoleRepository resourceRoleRepo;
    @Autowired
    private ProductResourceRepository prodResourceRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private ComponentRepository componentRepo;
    @Autowired
    private PriorityRepository priorityRepo;
    @Autowired
    private EpicRepository epicRepo;
    @Autowired
    private EpicEstimateRepository epicEstRepo;
    @Autowired
    private EpicAssignmentRepository epicAssignmentRepository;
    @Autowired
    private DesignationRepository designRepo;
    @Autowired
    private CompanyWeekendRepository companyWeekendRepository;
    @Autowired
    private CompanyWorkingHourRepository companyWorkingHourRepository;
    @Autowired
    private CompanyCalendarRepository companyCalRepository;
    @Autowired
    private ReleaseEpicHistoryRepository releaseEpicHistoryRepository;
    @Autowired
    private ReleaseRepository releaseRepository;
    @Autowired
    private ResourceLeaveRepository resourceLeavesRepository;
    @Autowired
    private ReleaseWorkingDayRepository releaseWorkingDayRepository;
    @Autowired
    private TimeLoggingRepository timeLoggingRepository;

    public List<AIBaseResponse> getUISuggestions(long resourceId, Long productId, AIRoute userRoute) {
        log.info(">>>>>>>>>>>>>>>>" + resourceId + "," + productId + "," + (userRoute != null ? userRoute.getValue() : null));
        List<AIBaseResponse> suggestions = new ArrayList<>();
        Resource r = resourceRepo.findById(resourceId).get();
        if (r.getRoleId() != null) {
            Role role = roleRepo.findById(r.getRoleId()).get();
            if (role.getCode() == RoleEnum.ADMIN) {
                suggestions = forAdmin(r, productId, userRoute);
            } else if (role.getCode() == RoleEnum.PM) {
                suggestions = forPM(r, productId, userRoute);
            }
        }
        // Route specific for all roles
        if (userRoute != null) {
            switch (userRoute) {
                case resource:
                    suggestions.add(new AIBaseResponse(INFO,
                            "Resources can be added using Excel File Upload.", upload_resource));
                case upload_resource:
                    suggestions.add(new AIBaseResponse(INFO,
                            "Resources can be added manually.", resource));
                case upload_epic:
                    suggestions.add(new AIBaseResponse(INFO,
                            "Deliverables can be added manually using Quick Add."));
            }
        }
        return suggestions;
       /* if (r.getRoleId())

        List<ProductResource> prs = prodResourceRepo.findByResourceIdAndActiveIsTrue(resourceId);
        if (bean.isGlobalAdmin() || bean.isGlobalHr() || bean.isGlobalManager()) {
            bean.setProducts(productRepo.findByCompanyIdAndActiveIsTrue(r.getCompanyId()));
        } else {
            bean.setProducts(
                    prs.stream().filter(pr -> pr.getParticipationPercentTime() > 0)
                            .map(pr -> productRepo.findById(pr.getProductId()).get())
                            .collect(Collectors.toList()));
        }
        if (productId != null) {
            prs.stream().filter(pr -> pr.getParticipationPercentTime() > 0 && pr.getProductId().equals(productId)).forEach(pr -> {
                Role role = roleRepo.findById(pr.getRoleId()).get();
                if (role.getCode() == RoleEnum.PM) {
                    bean.setProductManager(true);
                }
                if (role.getCode() == RoleEnum.ADMIN) {
                    bean.setProductAdmin(true);
                }
                if (role.getCode() == RoleEnum.HR) {
                    bean.setProductHr(true);
                }
                bean.setProductRoleId(role.getId());
            });
        }

        List<Product> prodsAsPM = productRepo.findByProductManagerIdAndActiveIsTrue(resourceId);
        prodsAsPM.forEach(p -> {
            if (productId != null && p.getId().equals(productId)) {
                bean.setProductManager(true);
            }
            if (!bean.getProducts().contains(p)) {
                bean.getProducts().add(p);
            }
        });
        return bean;*/
    }

    private List<AIBaseResponse> forAdmin(Resource r, Long productId, AIRoute userRoute) {
        List<AIBaseResponse> suggestions = new ArrayList<>();

        long compNonSystemRoleResources = resourceRepo.countByCompanyIdAndRoleIdIsNotSystemUser(r.getCompanyId());
        //long compResources = resourceRepo.countByCompanyId(r.getCompanyId());
        if (compNonSystemRoleResources == 0) {
            long compProducts = productRepo.countByCompanyId(r.getCompanyId());
            if (compProducts == 0) {
                //To add resources
                suggestions.add(new AIBaseResponse(ALARM,
                        "To add resources either use upload option or add manually.",
                        upload_resource,
                        resource
                ));
            } else {
                //To add resources
                suggestions.add(new AIBaseResponse(STOPPER,
                        "Resources should be added to define project team",
                        upload_resource,
                        resource
                ));
            }
        } else {
            //Long designations = designRepo.countByCompanyId(r.getCompanyId());
            //if (designations>1) {
            //  Long desigMapped = designRepo.countByCompanyIdAndRoleIdIsNotNull(r.getCompanyId());
            //if (desigMapped == 0) {
            // Role Mapping
            //suggestions.add(new AIBaseResponse(INFO,
            //      "Designation mapping to System Role should be updated.",
            //    mapping_roles_designation
            //));
            //}
            //}
        }
        suggestions.addAll(forPM(r, productId, userRoute));
        return suggestions;
    }

    private List<AIBaseResponse> forPM(Resource r, Long productId, AIRoute userRoute) {
        List<AIBaseResponse> suggestions = new ArrayList<>();
        if (productId != null) {
            long pResources = prodResourceRepo.countByProductIdAndActiveIsTrue(productId);
            if (pResources <= 1) { // PM is set on Product creation
                //To define team
                suggestions.add(new AIBaseResponse(ALARM,
                        "Define product team.",
                        team_resource));
            } else {
                long pResourcesWithTime = prodResourceRepo.findCountWithNonSystemOnlyRolesByProductIdWithParticipationPercentTime(productId);
                if (pResourcesWithTime == 0) {
                    suggestions.add(new AIBaseResponse(ALARM,
                            "Allocation time for product team.",
                            product_resource));
                } else {
                    if (userRoute == planning) {
                        Long unplannedEpics = epicRepo.countByProductIdAndReleaseIdIsNullAndActiveIsTrue(productId);
                        if (unplannedEpics == 0) {
                            Long unplannedR = releaseRepository.countByProductIdAndStatusAndActiveIsTrue(productId, ReleaseStatusEnum.UNPLANNED);
                            if (unplannedR == 0) {
                                suggestions.add(new AIBaseResponse(ALARM,
                                        "There is nothing in backlog to plan. Add deliverables through file or manually.",
                                        upload_epic));

                            }
                        }
                    }
                }
            }
        }
        return suggestions;
    }
}
