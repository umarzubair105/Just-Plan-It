package com.uz.justplan.lookup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PriorityRepository extends CrudRepository<Priority, Integer>, ListPagingAndSortingRepository<Priority, Integer> {

}
