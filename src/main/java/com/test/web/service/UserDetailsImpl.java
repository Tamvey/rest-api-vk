package com.test.web.service;

import com.test.web.enitiy.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserDetailsImpl implements UserDetails {
    private String username;

    private String password;

    private GrantedAuthority authority;

    private int enabled;

    public UserDetailsImpl(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authority = new LocalGrantedAuthority(user.getAuthority());
        this.enabled = user.getEnabled();
    }
    public UserDetailsImpl() {}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled != 0;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class LocalGrantedAuthority implements GrantedAuthority {
        private String authority;
        @Override
        public String getAuthority() {
            return authority;
        }

    }
}
