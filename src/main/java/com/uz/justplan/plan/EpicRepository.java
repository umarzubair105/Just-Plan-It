package com.uz.justplan.plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EpicRepository extends CrudRepository<Epic, Long> {
    Optional<Epic> findByProductIdAndTitleIgnoreCase(long productId, String title);

    // left join query with epicrelease
    List<Epic> findByProductIdAndReleaseIdIsNullAndActiveIsTrue(long productId);

    @Query("select e from Epic e, Product p where e.productId=p.id and p.companyId=:companyId and e.code=:code")
    List<Epic> findByCompanyIdAndCode(long companyId, String code);

    List<Epic> findByReleaseIdAndActiveIsTrue(long releaseId);

    @Query("select distinct e from Epic e, ReleaseEpicHistory ee where " +
            " ee.releaseId=:releaseId and e.id=ee.epicId and  e.active=true " +
            " and ee.active=true")
    List<Epic> findByReleaseIdAndActiveIsTrueUsingHistory(long releaseId);

    @Query("select distinct e from Epic e, EpicLink ee where " +
            " ee.epicId=:epicId and e.id=ee.linkedEpicId and  e.active=true " +
            " and ee.active=true")
    List<Epic> findByRelatedEpicsByEpicId(long epicId);

    Long countByReleaseIdAndActiveTrue(Long releaseId);

    Long countByProductId(Long productId);

    @Query("select code from Epic where id=:id")
    Optional<String> findCodeById(long id);

    @Query("select distinct e from Epic e, EpicAssignment ee where " +
            " e.releaseId=:releaseId and e.id=ee.epicId and  ee.resourceId=:resourceId and e.active=true " +
            " and ee.active=true")
    List<Epic> findAllByResourceIdAndReleaseId(Long resourceId, Long releaseId);

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
