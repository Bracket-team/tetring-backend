package com.bracket.tetring.global.oauth2.service;

import com.bracket.tetring.domain.player.domain.Player;
import com.bracket.tetring.domain.player.repository.PlayerRepository;
import com.bracket.tetring.global.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.bracket.tetring.global.oauth2.user.OAuth2UserInfo;
import com.bracket.tetring.global.oauth2.user.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final PlayerRepository playerRepository;

    //loadUser는 사용자의 정보를 가져오는 핵심 메소드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new InternalAuthenticationServiceException(exception.getMessage(), exception.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        //OAuth2 인증에 사용된 클라이언트 정보 획득 (카카오, 구글, 네이버 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth2 인증한 뒤에 발급한 access token 획득
        String accessToken = userRequest.getAccessToken().getTokenValue();

        //OAuth2UserInfoFactory를 이용하여 각 클라이언트에 맞는 OAuth2UserInfo 획득
        //각 클라이언트는 OAuth2UserInfo 인터페이스를 구현하고 있기에 정보 획득 가능
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId,
                accessToken,
                oAuth2User.getAttributes());

        // OAuth2UserInfo field value validation
        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        Player player = getOrSave(oAuth2UserInfo);

        return new OAuth2UserPrincipal(player, oAuth2UserInfo);
    }

    private Player getOrSave(OAuth2UserInfo oAuth2UserInfo) {
        Player player = playerRepository.findPlayerByEmail(oAuth2UserInfo.getEmail())
                .orElseGet(oAuth2UserInfo::toEntity);
        return playerRepository.save(player);
    }
}
