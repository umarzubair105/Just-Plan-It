package com.uz.justplan.plan;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EpicReleaseConcernRepository extends CrudRepository<EpicReleaseConcern, Long> {
    List<EpicReleaseConcern> findByReleaseIdAndActiveIsTrue(long releaseId);

    List<EpicReleaseConcern> findByEpicIdAndReleaseIdAndActiveIsTrue(long epicId, long releaseId);

    List<EpicReleaseConcern> findByEpicIdAndActiveIsTrue(long epicId);

}
