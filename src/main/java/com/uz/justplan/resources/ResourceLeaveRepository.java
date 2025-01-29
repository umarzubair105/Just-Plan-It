package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ResourceLeaveRepository extends CrudRepository<ResourceLeave, Long>,
        ListPagingAndSortingRepository<ResourceLeave, Long> {

}
