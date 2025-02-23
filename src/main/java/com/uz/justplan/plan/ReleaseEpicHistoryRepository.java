package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ReleaseEpicHistoryRepository extends CrudRepository<ReleaseEpicHistory, Long>,
        ListPagingAndSortingRepository<ReleaseEpicHistory, Long> {

}
