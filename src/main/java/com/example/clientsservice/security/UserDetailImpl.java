package com.example.clientsservice.security;

import com.example.clientsservice.models.User;
import com.example.clientsservice.models.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class UserDetailImpl implements UserDetails {
    private User user;

    public UserDetailImpl(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.stream(Role.values())
//                .map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toSet());
//        return Arrays.stream(Role.values())
//                .map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toSet());
        return new HashSet<>(List.of(new SimpleGrantedAuthority(user.getRole().name())));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus().equals(Status.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !user.getStatus().equals(Status.BANED);
    }
}
