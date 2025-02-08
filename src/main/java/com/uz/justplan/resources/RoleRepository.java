package com.uz.justplan.resources;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long>,
        ListPagingAndSortingRepository<Role, Long> {
    List<Role> findByCompanyIdAndActive(Long companyId, boolean active);

    @Query("SELECT r FROM Role r, ResourceRole rr WHERE rr.resourceId = :resourceId" +
            " and rr.roleId=r.id and rr.active=true")
    List<Role> findRolesByResourceId(@Param("resourceId") Long resourceId);
}
