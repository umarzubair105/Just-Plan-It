package com.uz.justplan.services;

import com.uz.justplan.config.security.CustomUserDetails;
import com.uz.justplan.resources.Resource;
import com.uz.justplan.resources.ResourceRepository;
import com.uz.justplan.resources.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] values = username.split(";");
        Resource user = resourceRepository.findByCompanyIdAndEmailIgnoreCaseAndActive(
                        Long.parseLong(values[1]), values[0], true)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorities =
                roleRepository.findRolesByResourceId(user.getId()).stream()
                        .map(role -> new SimpleGrantedAuthority(role.getCode().getValue()))
                        .collect(Collectors.collectingAndThen(Collectors.toList(),
                                Collections::unmodifiableList));
        //List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(""));
        //return new org.springframework.security.core.userdetails.User(
        return new CustomUserDetails(
                user.getId(),
                username.trim().toLowerCase(),
                user.getPassword(),
                user.getName(),
                authorities
                //List.of(authorities.iterator().next())
                //List.of(new SimpleGrantedAuthority("BA"))
                // Assign role
        );
    }
}
