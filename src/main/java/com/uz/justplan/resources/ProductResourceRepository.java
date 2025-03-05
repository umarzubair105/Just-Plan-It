package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface ProductResourceRepository extends CrudRepository<ProductResource, Long>,
        ListPagingAndSortingRepository<ProductResource, Long> {
    List<ProductResource> findByResourceId(long resourceId);

    List<ProductResource> findByResourceIdInAndActiveIsTrue(Collection<Long> resourceId);

    List<ProductResource> findByProductIdAndActive(long productId, boolean active);

    List<ProductResource> findByResourceIdAndActiveIsTrue(long resourceId);

}
