package com.uz.justplan.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;

public interface ContactUsRepository extends CrudRepository<ContactUs, Long>, ListPagingAndSortingRepository<ContactUs, Long> {

    List<ContactUs> findByAddressed(boolean addressed);
}
