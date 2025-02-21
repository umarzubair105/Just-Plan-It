package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface CompanyCalendarRepository extends CrudRepository<CompanyCalendar, Long>,
        ListPagingAndSortingRepository<CompanyCalendar, Long> {
    List<CompanyCalendar> findByCompanyIdAndActive(long companyId, boolean active);
}
