package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ReleaseEpicRepository extends CrudRepository<ReleaseEpic, Long>,
        ListPagingAndSortingRepository<ReleaseEpic, Long> {

}
