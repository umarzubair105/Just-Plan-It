package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface EpicRepository extends CrudRepository<Epic, Long>,
        ListPagingAndSortingRepository<Epic, Long> {

}
