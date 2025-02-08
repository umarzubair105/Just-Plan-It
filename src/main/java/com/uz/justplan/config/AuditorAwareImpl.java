package com.uz.justplan.config;

import com.uz.justplan.config.security.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        // 🔹 Retrieve the currently logged-in user (for example, from SecurityContextHolder)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of(1l); // Default value when no user is authenticated
        }
        //authentication.getName()
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return Optional.of(userDetails.getId()); // Return the username
    }
}
