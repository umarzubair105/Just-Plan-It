package com.uz.justplan.lookup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ReleaseStatusRepository extends CrudRepository<ReleaseStatus, Integer>, ListPagingAndSortingRepository<ReleaseStatus, Integer> {

}
