package com.bracket.tetring.domain.player.domain;

import com.bracket.tetring.global.oauth2.user.OAuth2Provider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private OAuth2Provider provider;

    private String username;
    private String email;
    private String profileImageUrl;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Player(OAuth2Provider provider, String username, String email, String profileImageUrl, Role role) {
        this.provider = provider;
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }
}
