package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface EpicRepository extends CrudRepository<Epic, Long>, ListPagingAndSortingRepository<Epic, Long> {
    Optional<Epic> findByProductIdAndDescriptionIgnoreCase(long productId, String description);
}
