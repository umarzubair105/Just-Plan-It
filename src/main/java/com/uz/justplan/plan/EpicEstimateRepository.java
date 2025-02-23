package com.uz.justplan.plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface EpicEstimateRepository extends CrudRepository<EpicEstimate, Long>,
        ListPagingAndSortingRepository<EpicEstimate, Long> {
    @Query("select sum(ee.hours) from Epic re, EpicEstimate ee where re.releaseId=:releaseId and re.active=true " +
            " and re.id=ee.epicId and ee.resourceId=:resourceId and ee.roleId=:roleId and ee.active=true")
    Double calculateTotalHoursByReleaseIdAndResourceIdAndRoleId(Long releaseId, Long resourceId, Long roleId);

    @Query("select ee from Epic e, EpicEstimate ee where e.releaseId=:releaseId and e.active=true " +
            " and e.id=ee.epicId and ee.active=true")
    List<EpicEstimate> findAllByReleaseId(Long releaseId);

    @Query("select ee from Epic e, EpicEstimate ee where e.productId=:productId and e.releaseId is null and e.active=true " +
            " and e.id=ee.epicId and ee.active=true")
    List<EpicEstimate> findUnplannedEpicEstimatedByProductId(Long productId);

}
