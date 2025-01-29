package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface CompanyCalendarRepository extends CrudRepository<CompanyCalendar, Long>,
        ListPagingAndSortingRepository<CompanyCalendar, Long> {

}
