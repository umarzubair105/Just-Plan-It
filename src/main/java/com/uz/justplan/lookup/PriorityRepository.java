package com.uz.justplan.lookup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PriorityRepository extends CrudRepository<Priority, Long>, ListPagingAndSortingRepository<Priority, Long> {
    Optional<Priority> findByCompanyIdAndNameIgnoreCase(long companyId, String name);

    Page<Priority> findByCompanyIdAndActive(long companyId, boolean active, Pageable pageable);

    List<Priority> findByCompanyIdAndActiveIsTrue(long companyId);
}
