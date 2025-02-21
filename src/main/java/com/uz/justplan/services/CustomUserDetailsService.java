package com.uz.justplan.services;

import com.uz.justplan.config.security.CustomUserDetails;
import com.uz.justplan.core.Company;
import com.uz.justplan.core.CompanyRepository;
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
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CompanyRepository compRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] values = username.split(";");
        Resource user = null;
        if (values.length == 1) {
            List<Resource> resources = resourceRepository.findByEmailIgnoreCaseAndActive(
                    values[0], true);
            Assert.isTrue(!resources.isEmpty(), "User does not exist..");
            Assert.isTrue(resources.size() == 1, "There are many users with the same email. Specify company code, please.");
            user = resources.get(0); // Assuming the first user is the one with active status
        } else {
            Optional<Company> company = compRepository.findByCodeAndActive(values[1], true);
            Assert.isTrue(company.isPresent(), "Company code does not exist.");
            Optional<Resource> userOpt = resourceRepository.findByCompanyIdAndEmailIgnoreCaseAndActive(
                    company.get().getId(), values[0], true);
//                    .orElseThrow(() -> new UsernameNotFoundException("User does not exist."));
            Assert.isTrue(userOpt.isPresent(), "User does not exist..");
            user = userOpt.get();
        }
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
                user.getCompanyId(),
                authorities
                //List.of(authorities.iterator().next())
                //List.of(new SimpleGrantedAuthority("BA"))
                // Assign role
        );
    }
}
