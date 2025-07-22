package com.paichai.health.common.jwt;

import com.paichai.health.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return user != null ? user.getUserId() : null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user != null && user.getRole() != null && user.getRole().getRoleId() != null) {
            return List.of(() -> "ROLE_" + user.getRole().getRoleId());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public String getPassword() {
        return user != null ? user.getPassword() : null;
    }

    @Override
    public String getUsername() {
        return user != null ? user.getEmail() : null;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    @Override
    public String toString() {
        return "[CustomUserDetails: " + getUsername() + ", id=" + getUserId() + "]";
    }
}
