package com.uz.justplan.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ProductRepository extends CrudRepository<Product, Long>,
        ListPagingAndSortingRepository<Product, Long> {

}
