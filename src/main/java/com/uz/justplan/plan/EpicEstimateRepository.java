package com.uz.justplan.plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface EpicEstimateRepository extends CrudRepository<EpicEstimate, Long>,
        ListPagingAndSortingRepository<EpicEstimate, Long> {


    @Query("select ee from Epic e, EpicEstimate ee where e.releaseId=:releaseId and e.active=true " +
            " and e.id=ee.epicId and ee.active=true")
    List<EpicEstimate> findAllByReleaseId(Long releaseId);

    @Query("select ee from Epic e, EpicEstimate ee where e.productId=:productId and e.releaseId is null and e.active=true " +
            " and e.id=ee.epicId and ee.active=true")
    List<EpicEstimate> findUnplannedEpicEstimatedByProductId(Long productId);

    List<EpicEstimate> findByEpicIdAndActiveIsTrue(Long epicId);

}
