package com.bracket.tetring.global.oauth2.user;

import com.bracket.tetring.domain.player.domain.Player;

import java.util.Map;

public interface OAuth2UserInfo {

    OAuth2Provider getProvider();

    String getAccessToken();

    Map<String, Object> getAttributes();

    String getId();

    String getEmail();

    String getName();

    String getFirstName();

    String getLastName();

    String getNickname();

    String getProfileImageUrl();

    public Player toEntity();
}

