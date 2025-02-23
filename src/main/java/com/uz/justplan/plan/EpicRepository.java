package com.uz.justplan.plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EpicRepository extends CrudRepository<Epic, Long> {
    Optional<Epic> findByProductIdAndTitleIgnoreCase(long productId, String title);

    // left join query with epicrelease
    List<Epic> findByProductIdAndReleaseIdIsNullAndActiveIsTrue(long productId);

    List<Epic> findByReleaseIdAndActiveIsTrue(long releaseId);

    @Query("select code from Epic where id=:id")
    Optional<String> findCodeById(long id);

    // what is issue with following query


/*
    @Query("SELECT  new com.uz.justplan.beans.response.EpicBean(e, p, e2.code, r.name) from Epic e left join Priority p on e.priorityId = p.id " +
            " left join Epic e2 on e.dependOnEpicId=e2.id left join Resource r on e.raisedByResourceId = r.id " +
            " where e.productId=:productId  and e.releaseId is null and e.active=true order by COALESCE(p.priorityLevel, 100) ");
*/
/*
    @Query("SELECT  new com.uz.justplan.beans.response.EpicBean(" +
            "e.id, e.code, e.title, e.details,e.dependOnEpicId,e.requiredBy, e.risks, e.forcefullyAdded, e.priorityId," +
            " p.name , p.priorityLevel,e.comments, r.name) from Epic e left join Priority p on e.priorityId = p.id " +
        "  left join Resource r on e.raisedByResourceId = r.id " +
        " where e.productId=:productId  and e.releaseId is null and e.active=true order by COALESCE(p.priorityLevel, 100) ")
*/



/*    @Query("SELECT  new com.uz.justplan.beans.response.EpicBean(e, p, e2.code, r.name) from Epic e left join Priority p on e.priorityId = p.id " +
            " left join Epic e2 on e.dependOnEpicId=e2.id left join Resource r on e.raisedByResourceId = r.id " +
            " where e.productId=:productId  and e.releaseId is null and e.active=true order by COALESCE(p.priorityLevel, 100) ")*/
/*@Query(name = "SELECT e.code from Epic e left join Priority p on e.priorityId = p.id " +
        " left join Resource r on e.raisedByResourceId = r.id " +
        " where e.productId=:productId  and e.releaseId is null and e.active=true order by COALESCE(p.priorityLevel, 100) ",
        nativeQuery = true)*/

}
