package com.uz.justplan.resources;

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

    List<Resource> findByIdInAndActive(Collection<Long> ids, boolean active);
    List<Resource> findByDesignationId(long designationId);

    Optional<Resource> findByCompanyIdAndEmailIgnoreCase(long companyId, String email);

    Optional<Resource> findByCompanyIdAndEmailIgnoreCaseAndActive(
            long companyId, String email, boolean active);

    List<Resource> findByEmailIgnoreCaseAndActive(String email, boolean active);

    @Query("select r from ProductResource pr, Resource r where pr.productId=:productId and pr.active=true" +
            " and pr.resourceId=r.id and r.active=true")
    List<Resource> findResourcesByProductId(long productId);

}
