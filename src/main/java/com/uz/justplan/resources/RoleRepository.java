package com.uz.justplan.resources;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long>,
        ListPagingAndSortingRepository<Role, Long> {
    List<Role> findByCompanyIdAndActive(Long companyId, boolean active);

    @Query("SELECT r FROM Role r, Resource rr WHERE rr.id = :resourceId" +
            " and rr.roleId=r.id and rr.active=true")
    List<Role> findRolesByResourceId(@Param("resourceId") Long resourceId);

    @Query("select distinct role from ProductResource pr, Resource r, Role role where pr.productId=:productId and pr.active=true " +
            " and pr.resourceId=r.id and r.active=true and pr.roleId=role.id and role.systemRole=false and r.status='ACTIVE' and role.active=true")
    List<Role> findActiveNonSystemOnlyRolesByProductId(long productId);

    List<Role> findByCompanyIdAndActiveIsTrueAndSystemRoleIsTrue(Long companyId);
}
