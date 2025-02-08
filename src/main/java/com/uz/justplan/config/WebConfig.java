package com.uz.justplan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow CORS for all endpoints
                        .allowedOrigins("http://localhost:4200") // Allow requests from Angular
                        //.allowedOrigins("*") // Allow requests from Angular
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // Allowed HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        //.allowedHeaders("Authorization", "Content-Type","TestH") // Allow all headers
                        //.exposedHeaders("Authorization")
                        .allowCredentials(true); // Allow credentials (if needed)
            }
        };
    }
}
