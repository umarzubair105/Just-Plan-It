package com.uz.justplan.lookup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Integer>, ListPagingAndSortingRepository<Country, Integer> {
    List<Country> findAllByActive(boolean active);
}
