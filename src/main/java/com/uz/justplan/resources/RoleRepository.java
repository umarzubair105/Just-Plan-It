package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long>,
        ListPagingAndSortingRepository<Role, Long> {
    List<Role> findByCompanyIdAndActive(Long companyId, boolean active);
}
