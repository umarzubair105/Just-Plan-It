package com.uz.justplan.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long>,
        ListPagingAndSortingRepository<Product, Long> {
    List<Product> findByCompanyIdAndActiveIsTrue(long companyId);

    Optional<Product> findByCompanyIdAndNameIgnoreCase(long companyId, String name);

    Long countByCompanyId(long companyId);

    List<Product> findByProductManagerIdAndActiveIsTrue(long resourceId);
}
