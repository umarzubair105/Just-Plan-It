package com.uz.justplan.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class CustomUserDetails extends User {
    private Long id, companyId;
    private String name;

    public CustomUserDetails(Long id, String username, String password, String name, long companyId, List<GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.name = name;
        this.id = id;
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
