package com.uz.justplan.resources;

import com.uz.justplan.lookup.ResourceStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends CrudRepository<Resource, Long>,
        ListPagingAndSortingRepository<Resource, Long> {
    List<Resource> findByCompanyId(long companyId);

    List<Resource> findByCompanyIdAndActiveIsTrue(long companyId);

    Long countByCompanyId(long companyId);

    List<Resource> findByIdInAndStatusAndActive(Collection<Long> ids, ResourceStatus status, boolean active);

    List<Resource> findByDesignationIdAndActiveIsTrue(long designationId);

    //Optional<Resource> findByCompanyIdAndEmailIgnoreCase(long companyId, String email);

    //Optional<Resource> findByCompanyIdAndEmailIgnoreCaseAndActive(
    //      long companyId, String email, boolean active);


    Optional<Resource> findOneByEmailIgnoreCaseAndActiveIsTrue(String email);

    Optional<Resource> findOneByCompanyIdAndEmailIgnoreCaseAndStatus(long companyId, String email, ResourceStatus status);

    List<Resource> findByEmailIgnoreCaseAndStatusAndActiveIsTrue(String email, ResourceStatus status);

    @Query("select r from ProductResource pr, Resource r, Role role where pr.productId=:productId and pr.active=true " +
            " and pr.resourceId=r.id and r.active=true and pr.roleId=role.id and role.systemRole=false and r.status='ACTIVE' and role.active=true")
    List<Resource> findActiveNonSystemOnlyResourcesByProductId(long productId);


    @Query("select r from ProductResource pr, Resource r where pr.productId=:productId and pr.active=true " +
            " and pr.resourceId=r.id and r.active=true")
    List<Resource> findResourcesByProductId(long productId);
    List<Resource> findByLeadResourceIdAndActiveIsTrue(long leadResourceId);

}
