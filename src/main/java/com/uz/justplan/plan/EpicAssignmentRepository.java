package com.uz.justplan.plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface EpicAssignmentRepository extends CrudRepository<EpicAssignment, Long>,
        ListPagingAndSortingRepository<EpicAssignment, Long> {

    @Query("select COALESCE(SUM(ee.estimate), 0) from Epic re, EpicAssignment ee where re.releaseId=:releaseId and re.active=true " +
            " and re.id=ee.epicId and ee.resourceId=:resourceId and ee.roleId=:roleId and ee.active=true")
    Double calculateTotalHoursByReleaseIdAndResourceIdAndRoleId(Long releaseId, Long resourceId, Long roleId);


    @Query("select ee from Epic e, EpicAssignment ee where e.releaseId=:releaseId and e.active=true " +
            " and e.id=ee.epicId and ee.active=true")
    List<EpicAssignment> findAllByReleaseId(Long releaseId);

/*
    @Query("select ee from Epic e, EpicEstimate ee where e.productId=:productId and e.releaseId is null and e.active=true " +
            " and e.id=ee.epicId and ee.active=true")
    List<EpicEstimate> findUnplannedEpicEstimatedByProductId(Long productId);
*/

}
