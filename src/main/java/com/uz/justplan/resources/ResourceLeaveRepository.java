package com.uz.justplan.resources;

import com.uz.justplan.lookup.LeaveStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface ResourceLeaveRepository extends CrudRepository<ResourceLeave, Long>,
        ListPagingAndSortingRepository<ResourceLeave, Long> {

    List<ResourceLeave> findResourceLeavesByResourceIdInAndActiveAndEndDateGreaterThanEqualAndStatus(
            Collection<Long> resourceIds, boolean active, LocalDate endDateGreaterThanEqual,
            LeaveStatus status);

}
