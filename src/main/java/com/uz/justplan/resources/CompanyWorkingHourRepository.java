package com.uz.justplan.resources;

import com.uz.justplan.lookup.WorkingHourScope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;
import java.util.Set;

public interface CompanyWorkingHourRepository extends CrudRepository<CompanyWorkingHour, Long>,
        ListPagingAndSortingRepository<CompanyWorkingHour, Long> {
    Set<CompanyWorkingHour> findByCompanyIdAndActive(Long companyId, boolean active);

    List<CompanyWorkingHour> findByCompanyIdAndScopeAndActive(Long companyId, WorkingHourScope scope, boolean active);
}
