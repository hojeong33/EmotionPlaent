package com.ssafy.project.EmotionPlanet.Config.OAuth;

import com.ssafy.project.EmotionPlanet.Config.auth.PrincipalDetails;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserService userService;

    PrincipalOauth2UserService(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder,
                               @Lazy UserService userService){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration: " + userRequest.getClientRegistration());
        System.out.println("getAccessToken: " + userRequest.getAccessToken().getTokenValue());
        System.out.println("getAttributes: " + super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getClientId(); // google
        String providerId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
        String nickname = provider + "_" + providerId;
        String password = bCryptPasswordEncoder.encode("겟인데아");
        String role = "ROLE_USER";

        UserDto userEntity = userService.userSelectByEmail(email);

        if (userEntity == null) {
            userEntity = UserDto.builder()
                    .nickname(nickname)
                    .pw(password)
                    .email(email)
                    .provider(provider)
                    .build();
            userService.userRegister(userEntity);
        }

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
