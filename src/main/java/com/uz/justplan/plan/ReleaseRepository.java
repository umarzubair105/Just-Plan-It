package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface ReleaseRepository extends CrudRepository<Release, Long>,
        ListPagingAndSortingRepository<Release, Long> {
    Optional<Release> findTopOneByProductIdAndActiveOrderByStartDateDesc(long productId, boolean active);
}
