package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ProductResourceRepository extends CrudRepository<ProductResource, Long>,
        ListPagingAndSortingRepository<ProductResource, Long> {

}
