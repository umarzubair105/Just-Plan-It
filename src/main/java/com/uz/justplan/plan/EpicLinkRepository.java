package com.uz.justplan.plan;

import com.uz.justplan.lookup.EpicLinkType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface EpicLinkRepository extends CrudRepository<EpicLink, Long>,
        ListPagingAndSortingRepository<EpicLink, Long> {

    List<EpicLink> findByEpicIdAndLinkTypeAndActiveIsTrueOrderByCreatedDateAsc(long epicId, EpicLinkType linkType);

    List<EpicLink> findByEpicIdAndActiveIsTrueOrderByCreatedDateAsc(long epicId);

}
