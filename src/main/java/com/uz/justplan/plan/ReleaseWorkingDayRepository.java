package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ReleaseWorkingDayRepository extends CrudRepository<ReleaseWorkingDay, Long>,
        ListPagingAndSortingRepository<ReleaseWorkingDay, Long> {
    Long countByReleaseIdAndActive(Long releaseId, Boolean active);
}
