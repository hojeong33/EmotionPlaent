package com.ssafy.project.EmotionPlanet.Config.OAuth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.ssafy.project.EmotionPlanet.Config.auth.PrincipalDetails;
import com.ssafy.project.EmotionPlanet.Dao.UserDao;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;

@Service
public class PrincipalOauth2UserService {

    @Autowired
    UserDao userDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    PrincipalOauth2UserService(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private final NetHttpTransport transport = new NetHttpTransport();
    private final JsonFactory jsonFactory = new GsonFactory();

    @Value("${google.client-id}")
    private String clientId;

    public UserDto tokenVerify(String idToken) {

        System.out.println("idToken : " + clientId);

        GoogleIdTokenVerifier gitVerifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setIssuers(Arrays.asList("https://accounts.google.com", "accounts.google.com"))
                .setAudience(Collections.singletonList(clientId))
                .build();

        GoogleIdToken git = null;

        try {
            git = gitVerifier.verify(idToken);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserDto user = new UserDto();
        if (git == null) {
            System.out.println("Google ID Token is invalid");
        }else {
            GoogleIdToken.Payload payload = git.getPayload();

            // Print user identifier & Get profile information from payload
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("sub");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String pw = bCryptPasswordEncoder.encode("security");

            user.setEmail(email);
            user.setNickname(name);
            user.setProfileImg(pictureUrl);
            user.setPw(pw);
        }
        return user;
    }

    public int insertUser(UserDto userDto) {
        if (userDao.duplicateEmail(userDto.getEmail()) == 0) {
            int result = userDao.userRegister(userDto);
            return result;
        } else {
            return 1;
        }
    }

}
