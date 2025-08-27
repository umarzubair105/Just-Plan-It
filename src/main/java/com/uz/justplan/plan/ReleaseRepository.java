package com.uz.justplan.plan;

import com.uz.justplan.lookup.ReleaseStatusEnum;
import org.springframework.data.jpa.repository.Query;
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

    @Query("select distinct r from Epic e, EpicAssignment ee, Release r where r.status=:releaseStatusEnum and r.active=true " +
            " and e.releaseId=r.id and e.id=ee.epicId and  ee.resourceId=:resourceId and e.active=true " +
            " and ee.active=true")
    List<Release> findAllByResourceIdAndReleaseStatus(Long resourceId, ReleaseStatusEnum releaseStatusEnum);


    @Query("select distinct r from Epic e, EpicLink ee, Release r where " +
            " ee.epicId=:epicId and e.id=ee.linkedEpicId and  e.active=true " +
            " and ee.active=true and e.releaseId=r.id")
    List<Release> findOfRelatedEpicReleaseEpicId(long epicId);


    Long countByProductIdAndStatusAndActiveIsTrue(long productId, ReleaseStatusEnum status);


}
