package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface EpicEstimateRepository extends CrudRepository<EpicEstimate, Long>,
        ListPagingAndSortingRepository<EpicEstimate, Long> {

}
