package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ReleaseEpicAssignmentRepository extends CrudRepository<ReleaseEpicAssignment, Long>,
        ListPagingAndSortingRepository<ReleaseEpicAssignment, Long> {

}
