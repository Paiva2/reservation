package org.com.reservation.application.config.security;

import org.com.reservation.domain.entity.Role;
import org.com.reservation.domain.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final String password;
    private final String id;
    private final Boolean accountEnabled;
    private final List<UserRole> roles;

    public UserDetailsImpl(String password, String id, Boolean accountEnabled, List<UserRole> roles) {
        this.password = password;
        this.id = id;
        this.accountEnabled = accountEnabled;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> rolesAssigned = this.roles.stream().map(UserRole::getRole).toList();
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : rolesAssigned) {
            String prefixRole = "ROLE_" + role.getName();
            authorities.add(new SimpleGrantedAuthority(prefixRole));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.accountEnabled;
    }

    @Override
    public boolean isEnabled() {
        return this.accountEnabled;
    }
}
