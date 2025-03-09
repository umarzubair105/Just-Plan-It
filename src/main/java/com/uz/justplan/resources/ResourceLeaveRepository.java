package com.uz.justplan.resources;

import com.uz.justplan.lookup.LeaveStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface ResourceLeaveRepository extends CrudRepository<ResourceLeave, Long>,
        ListPagingAndSortingRepository<ResourceLeave, Long> {

    List<ResourceLeave> findResourceLeavesByResourceIdInAndActiveAndEndDateGreaterThanEqualAndStartDateLessThanEqualAndStatus(
            Collection<Long> resourceIds, boolean active, LocalDate endDateGreaterThanEqual, LocalDate startDateLessThanEqual,
            LeaveStatus status);


    List<ResourceLeave> findByResourceIdAndActiveIsTrue(Long resourceId);
}
