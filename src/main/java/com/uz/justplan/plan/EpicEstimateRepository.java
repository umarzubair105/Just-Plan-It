package com.uz.justplan.plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface EpicEstimateRepository extends CrudRepository<EpicEstimate, Long>,
        ListPagingAndSortingRepository<EpicEstimate, Long> {
    @Query("select sum(ee.hours) from ReleaseEpic re, EpicEstimate ee where re.releaseId=:releaseId and re.active=true " +
            " and re.epicId=ee.epicId and ee.resourceId=:resourceId and ee.roleId=:roleId and ee.active=true")
    Double calculateTotalHoursByEpicIdAndResourceIdAndRoleId(Long releaseId, Long resourceId, Long roleId);
}
