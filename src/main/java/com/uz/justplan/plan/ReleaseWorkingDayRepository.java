package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface ReleaseWorkingDayRepository extends CrudRepository<ReleaseWorkingDay, Long>,
        ListPagingAndSortingRepository<ReleaseWorkingDay, Long> {
    Long countByReleaseIdAndActive(Long releaseId, Boolean active);

    List<ReleaseWorkingDay> findReleaseWorkingDayByReleaseIdAndActive(Long releaseId, Boolean active);
}
