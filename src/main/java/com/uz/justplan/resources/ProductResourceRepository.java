package com.uz.justplan.resources;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface ProductResourceRepository extends CrudRepository<ProductResource, Long>,
        ListPagingAndSortingRepository<ProductResource, Long> {
    List<ProductResource> findByResourceId(long resourceId);

    List<ProductResource> findByResourceIdInAndActiveIsTrue(Collection<Long> resourceId);

    List<ProductResource> findByProductIdAndActive(long productId, boolean active);


    @Query("select distinct pr from ProductResource pr, Resource r, Role role where pr.productId=:productId and pr.active=true " +
            " and pr.resourceId=r.id and r.active=true and pr.roleId=role.id and role.systemRole=false and role.active=true")
    List<ProductResource> findPRWithNonSystemOnlyRolesByProductId(long productId);

    List<ProductResource> findByResourceIdAndActiveIsTrue(long resourceId);

}
