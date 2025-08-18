package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface ReleaseResourceRepository extends CrudRepository<ReleaseResource, Long>,
        ListPagingAndSortingRepository<ReleaseResource, Long> {
    List<ReleaseResource> findByReleaseIdAndActiveIsTrue(long releaseId);

}
