package com.uz.justplan.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long>,
        ListPagingAndSortingRepository<Product, Long> {
    List<Product> findByCompanyIdAndActiveIsTrue(long productId);
}
