package com.uz.justplan.core;

import com.uz.justplan.beans.projections.BasicProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long>, ListPagingAndSortingRepository<Company, Long> {
    List<BasicProjection> findAllBySample(boolean sample);

    BasicProjection findProjectionById(long id);

    Optional<Company> findByCodeAndActive(String code, boolean active);

    Optional<Company> findByNameIgnoreCaseAndActive(String name, boolean active);
}
