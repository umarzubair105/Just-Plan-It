package com.uz.justplan.config;

import com.uz.justplan.core.Company;
import com.uz.justplan.core.Component;
import com.uz.justplan.core.ContactUs;
import com.uz.justplan.core.Product;
import com.uz.justplan.lookup.Country;
import com.uz.justplan.lookup.Priority;
import com.uz.justplan.lookup.ReleaseStatus;
import com.uz.justplan.plan.*;
import com.uz.justplan.resources.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        config.exposeIdsFor(Country.class, Company.class, Component.class, Product.class, Epic.class, Release.class,
                ReleaseEpicHistory.class, Resource.class, Role.class, EpicEstimate.class, Designation.class,
                ReleaseStatus.class, CompanyCalendar.class, CompanyWeekend.class,
                Priority.class, CompanyWorkingHour.class,
                TimeLogging.class, EpicDetail.class, EpicLink.class,
                ProductResource.class, Resource.class, ResourceLeave.class, Role.class,
                EpicAssignment.class, ContactUs.class, ReleaseResource.class,
                EpicReleaseConcern.class);

    }

}