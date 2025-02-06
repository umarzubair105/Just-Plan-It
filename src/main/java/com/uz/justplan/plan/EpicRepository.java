package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface EpicRepository extends CrudRepository<Epic, Long>, ListPagingAndSortingRepository<Epic, Long> {
    Optional<Epic> findByProductIdAndTitleIgnoreCase(long productId, String title);
}
