package com.uz.justplan.plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeLoggingRepository extends CrudRepository<TimeLogging, Long>,
        ListPagingAndSortingRepository<TimeLogging, Long> {
    List<TimeLogging> findByReleaseIdAndEpicIdAndResourceIdAndActiveIsTrueOrderByCreatedDateDesc(
            Long releaseId, Long epicId, Long resourceId);

    @Query("SELECT SUM(t.minutes) FROM TimeLogging t " +
            "WHERE t.releaseId = :releaseId AND t.epicId = :epicId AND t.resourceId = :resourceId AND t.active = true")
    Long findTotalMinutesByReleaseIdAndEpicIdAndResourceId(@Param("releaseId") Long releaseId,
                                                           @Param("epicId") Long epicId,
                                                           @Param("resourceId") Long resourceId);

    @Query("SELECT SUM(t.minutes) FROM TimeLogging t " +
            "WHERE t.epicId = :epicId AND t.resourceId = :resourceId AND t.active = true")
    Long findTotalMinutesByEpicIdAndResourceId(@Param("epicId") Long epicId,
                                               @Param("resourceId") Long resourceId);

    @Query("SELECT SUM(t.minutes) FROM TimeLogging t " +
            "WHERE t.releaseId = :releaseId AND t.resourceId = :resourceId AND t.active = true")
    Long findTotalMinutesByReleaseIdAndResourceId(@Param("releaseId") Long releaseId,
                                                  @Param("resourceId") Long resourceId);
}
