package com.uz.justplan.plan;

import com.uz.justplan.lookup.ReleaseStatusEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReleaseRepository extends CrudRepository<Release, Long>,
        ListPagingAndSortingRepository<Release, Long> {
    Optional<Release> findTopOneByProductIdAndActiveOrderByStartDateDesc(long productId, boolean active);

    List<Release> findByProductIdAndStatusAndActiveIsTrueOrderByStartDateAsc(long productId, ReleaseStatusEnum status);
    List<Release> findByProductIdAndStatusInAndActiveIsTrueOrderByStartDateAsc(long productId, Collection<ReleaseStatusEnum> status);


}
