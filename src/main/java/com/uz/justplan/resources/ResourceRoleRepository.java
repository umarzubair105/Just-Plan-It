package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface ResourceRoleRepository extends CrudRepository<ResourceRole, Long>,
        ListPagingAndSortingRepository<ResourceRole, Long> {
    Optional<ResourceRole> findByResourceIdAndRoleId(long resourceId, long roleId);
}
