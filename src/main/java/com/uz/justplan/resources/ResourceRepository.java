package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface ResourceRepository extends CrudRepository<Resource, Long>,
        ListPagingAndSortingRepository<Resource, Long> {
    List<Resource> findByCompanyId(long companyId);
}
