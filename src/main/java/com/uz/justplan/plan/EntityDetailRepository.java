package com.uz.justplan.plan;

import com.uz.justplan.lookup.EntityDetailType;
import com.uz.justplan.lookup.EntityType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface EntityDetailRepository extends CrudRepository<EntityDetail, Long>,
        ListPagingAndSortingRepository<EntityDetail, Long> {

    List<EntityDetail> findByEntityTypeAndEntityIdAndActiveIsTrueOrderByCreatedDateDesc(EntityType entityType,
                                                                                        long entityId);


    List<EntityDetail> findByEntityTypeAndEntityIdAndDetailTypeAndActiveIsTrueOrderByCreatedDateDesc(EntityType entityType,
                                                                                                     long entityId,
                                                                                                     EntityDetailType detailType);

    List<EntityDetail> findByEntityTypeAndEntityIdAndActiveIsTrueOrderByCreatedDateAsc(EntityType entityType,
                                                                                       long entityId);


    List<EntityDetail> findByEntityTypeAndEntityIdAndDetailTypeAndActiveIsTrueOrderByCreatedDateAsc(EntityType entityType,
                                                                                                    long entityId,
                                                                                                    EntityDetailType detailType);
}
