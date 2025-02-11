package com.uz.justplan.resources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends CrudRepository<Resource, Long>,
        ListPagingAndSortingRepository<Resource, Long> {
    List<Resource> findByCompanyId(long companyId);

    List<Resource> findByDesignationId(long designationId);

    Optional<Resource> findByCompanyIdAndEmailIgnoreCase(long companyId, String email);

    Optional<Resource> findByCompanyIdAndEmailIgnoreCaseAndActive(
            long companyId, String email, boolean active);

    List<Resource> findByEmailIgnoreCaseAndActive(String email, boolean active);

}
