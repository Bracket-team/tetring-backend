package com.bracket.tetring.global.oauth2.service;

import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.global.oauth2.user.OAuth2UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public record OAuth2UserPrincipal(Player player, OAuth2UserInfo userInfo) implements OAuth2User, UserDetails {

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userInfo.getEmail();
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
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return userInfo.getAttributes();
    }

    @Override
    public String getName() {
        return userInfo.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(player.getRole().getKey()));
    }
}
