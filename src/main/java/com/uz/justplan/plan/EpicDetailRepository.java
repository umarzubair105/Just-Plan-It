package com.uz.justplan.plan;

import com.uz.justplan.lookup.EpicDetailType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface EpicDetailRepository extends CrudRepository<EpicDetail, Long>,
        ListPagingAndSortingRepository<EpicDetail, Long> {

    List<EpicDetail> findByEpicIdAndDetailTypeAndActiveIsTrueOrderByCreatedDateAsc(long epicId, EpicDetailType detailType);

    List<EpicDetail> findByEpicIdAndActiveIsTrueOrderByCreatedDateAsc(long epicId);

    List<EpicDetail> findByEpicIdAndActiveIsTrueOrderByCreatedDateDesc(long epicId);

}
