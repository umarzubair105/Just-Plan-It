package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ReleaseRepository extends CrudRepository<Release, Long>,
        ListPagingAndSortingRepository<Release, Long> {

}
