package com.uz.justplan.lookup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface CountryRepository extends CrudRepository<Country, Integer>, ListPagingAndSortingRepository<Country, Integer> {

}
