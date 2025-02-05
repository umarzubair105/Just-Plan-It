package com.uz.justplan.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface DesignationRepository extends CrudRepository<Designation, Long>,
        ListPagingAndSortingRepository<Designation, Long> {
    Page<Designation> findByCompanyId(long companyId, Pageable pageable);

    Optional<Designation> findByCompanyIdAndNameIgnoreCase(long companyId, String name);
}
