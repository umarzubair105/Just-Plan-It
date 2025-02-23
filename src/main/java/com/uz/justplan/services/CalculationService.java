package com.uz.justplan.services;

import com.uz.justplan.beans.cal.ResourceCapInRelease;
import com.uz.justplan.core.CompanyRepository;
import com.uz.justplan.core.ComponentRepository;
import com.uz.justplan.core.Product;
import com.uz.justplan.core.ProductRepository;
import com.uz.justplan.lookup.*;
import com.uz.justplan.plan.*;
import com.uz.justplan.resources.*;
import com.uz.justplan.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CalculationService {

    @Autowired
    private CompanyRepository compRepo;
    @Autowired
    private ResourceRepository resourceRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ResourceRoleRepository resourceRoleRepo;
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
    private DesignationRepository designRepo;
    @Autowired
    private CompanyWeekendRepository companyWeekendRepository;
    @Autowired
    private CompanyWorkingHourRepository companyWorkingHourRepository;
    @Autowired
    private CompanyCalendarRepository companyCalRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ReleaseRepository releaseRepository;
    @Autowired
    private ResourceLeaveRepository resourceLeavesRepository;
    @Autowired
    private ReleaseWorkingDayRepository releaseWorkingDayRepository;

    public List<ResourceCapInRelease> getResourceCapInRelease(long releaseId) {
        //find the release
        Release release = releaseRepository.findById(releaseId).orElse(null);
        if (release == null) {
            throw new IllegalArgumentException("Release not found");
        }
        // find the content of the product
        Product product = productRepo.findById(release.getProductId()).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        //find the role in company
        Map<Long, Role> roleMap = roleRepo.findByCompanyIdAndActive(product.getCompanyId(), true)
                .stream().collect(Collectors.toMap(Role::getId, Function.identity()));
        // find the resources in the product with ParticipationPercentTime>0
        List<ProductResource> proResources = prodResourceRepo.findByProductIdAndActive(release.getProductId(), true)
                .stream().filter(pr -> pr.getParticipationPercentTime() > 0).collect(Collectors.toList());
        if (proResources == null || proResources.isEmpty()) {
            throw new IllegalArgumentException("No resources found for the product");
        }
        //find the resources in product resources
        List<Resource> resources = resourceRepo.findByIdInAndActive(
                proResources.stream().mapToLong(ProductResource::getResourceId).boxed()
                        .distinct().collect(Collectors.toList()), true);
        // find Roles of resources
        //List<ResourceRole> resRoles = resourceRoleRepo.findByResourceIdInAndActive(
        //        resources.stream().mapToLong(Resource::getId).boxed().collect(Collectors.toList()), true);
        //find resource leaves of resources
        // chk it ***********
        List<ResourceLeave> resLeaves = resourceLeavesRepository
                .findResourceLeavesByResourceIdInAndActiveAndEndDateGreaterThanEqualAndStatus(
                        resources.stream().mapToLong(Resource::getId).boxed().collect(Collectors.toList()),
                        true, release.getStartDate(), LeaveStatus.APPROVED);
        // find release workingday by releaseId
        List<ReleaseWorkingDay> releaseWorkingDays = releaseWorkingDayRepository
                .findReleaseWorkingDayByReleaseIdAndActive(releaseId, true);
        //             .filter(Objects::nonNull)
        List<ResourceCapInRelease> beans = new ArrayList<>();

        for (ProductResource prodResource : proResources) {
            ResourceCapInRelease bean = new ResourceCapInRelease();
            bean.roleId = prodResource.getRoleId();
            bean.roleName = roleMap.get(prodResource.getRoleId()).getName();
            bean.resourceId = prodResource.getResourceId();
            bean.resourceName = resources.stream().filter(rr -> rr.getId().equals(prodResource.getResourceId()))
                    .map(Resource::getName)
                    .findFirst().orElse("");
            // find capacity in release

            List<ResourceLeave> leaves = resLeaves.stream().filter(l -> l.getResourceId().equals(prodResource.getResourceId()))
                    .collect(Collectors.toList());

            int capacity = 0;
            for (ReleaseWorkingDay wd : releaseWorkingDays) {
                // is today within or equal to start date and end date
                Optional<ResourceLeave> rl = leaves.stream().filter(l -> Utils.isWithinOrEqualToStartEndDates(
                        wd.getWorkingDate(), l.getStartDate(), l.getEndDate())).findFirst();
                if (rl.isPresent()) {
                    if (rl.get().getLeaveType() == LeaveType.SHORT_LEAVE) {
                        capacity += wd.getMinutes() / 2;
                    }
                } else {
                    capacity += wd.getMinutes();
                }
            }
            // and based on multiple roles and witin differnt team across product user can perform.
            capacity = (capacity / 100) * prodResource.getParticipationPercentTime();
            bean.minutesForOtherActivities = (capacity / 100) * product.getOtherActivitiesPercentTime();
            bean.minutesCapacity = capacity - bean.minutesForOtherActivities;
            bean.minutesOccupied = epicEstRepo.calculateTotalHoursByReleaseIdAndResourceIdAndRoleId(releaseId,
                    prodResource.getResourceId(), prodResource.getRoleId()).intValue() * 60;
            // check if capacity is more than 0 (to avoid division by zero error and to avoid adding empty bean to the list 100% capacity resources will be excluded by default in the frontend))
            //if (bean.minutesCapacity>0) {
            beans.add(bean);
            //}
        }
        // now update capacity based on resource allocation % in product.
        //
        return beans;
    }


    @Transactional
    public void addReleases(long productId) {
        Product product = productRepo.findById(productId).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        // find product releases
        Optional<Release> lastSavedRelease = releaseRepository.findTopOneByProductIdAndActiveOrderByStartDateDesc(productId, true);
        // find the holidays
        Set<DayOfWeek> weekends = companyWeekendRepository.findByCompanyIdAndActive(product.getCompanyId(), true)
                .stream().map(CompanyWeekend::getDay).collect(Collectors.toSet());
        // find the working hours
        Set<CompanyWorkingHour> workingHours = companyWorkingHourRepository.findByCompanyIdAndActive(
                product.getCompanyId(), true);
        List<CompanyCalendar> compCals = companyCalRepository.findByCompanyIdAndActive(product.getCompanyId(), true);
        Set<LocalDate> holidays = new HashSet<>();
        Set<LocalDate> forcedWorkingDays = new HashSet<>();
        for (CompanyCalendar cc : compCals) {
            // add forced working days
            List<LocalDate> days = cc.getStartDate().datesUntil(cc.getEndDate().plusDays(1))
                    .collect(Collectors.toList());
            if (cc.getEventType() == EventType.WORKDAY) {
                forcedWorkingDays.addAll(days);
            } else {
                holidays.addAll(days);

            }
        }
        // find the country
        List<Release> newReleases = Utils.nextReleases(product.getStartDate(),
                product.getEndDate(),
                product.getReleaseIteration(),
                weekends,
                holidays,
                forcedWorkingDays,
                workingHours,
                lastSavedRelease.map(Release::getEndDate).orElse(null),//nullable
                lastSavedRelease.isPresent() ? lastSavedRelease.get().getVersion() : 0);
        newReleases.forEach(newRelease -> {
            newRelease.setProductId(productId);
            newRelease.setStatus(ReleaseStatusEnum.INITIATED);
            releaseRepository.save(newRelease);
            newRelease.getWorkingDaysList().forEach(wd -> {
                wd.setReleaseId(newRelease.getId());
                wd.setActive(true);
                releaseWorkingDayRepository.save(wd);
            });
        });
        // find the product
    }
}
