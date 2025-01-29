package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface CompanyWeekendRepository extends CrudRepository<CompanyWeekend, Long>,
        ListPagingAndSortingRepository<CompanyWeekend, Long> {
    List<CompanyWeekend> findByCompanyIdAndActive(Long companyId, boolean active);
}
