package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface CompanyWorkingHourRepository extends CrudRepository<CompanyWorkingHour, Long>,
        ListPagingAndSortingRepository<CompanyWorkingHour, Long> {
    List<CompanyWorkingHour> findByCompanyIdAndActive(Long companyId, boolean active);
}
