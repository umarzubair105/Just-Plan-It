package com.uz.justplan.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ComponentRepository extends CrudRepository<Component, Long>, ListPagingAndSortingRepository<Component, Long> {
    Optional<Component> findByCompanyIdAndNameIgnoreCase(long companyId, String name);

    List<Component> findByCompanyIdAndActiveIsTrue(long companyId);
}
