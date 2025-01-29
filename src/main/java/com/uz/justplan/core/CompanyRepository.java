package com.uz.justplan.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface CompanyRepository extends CrudRepository<Company, Long>, ListPagingAndSortingRepository<Company, Long> {

}
