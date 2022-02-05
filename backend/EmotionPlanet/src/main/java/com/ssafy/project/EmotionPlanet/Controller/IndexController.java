package com.ssafy.project.EmotionPlanet.Controller;
import com.ssafy.project.EmotionPlanet.Config.JWT.JwtService;
import com.ssafy.project.EmotionPlanet.Config.OAuth.PrincipalOauth2UserService;
import com.ssafy.project.EmotionPlanet.Config.auth.PrincipalDetails;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
        RequestMethod.OPTIONS })
@Controller
public class IndexController {

    @Autowired
    PrincipalOauth2UserService principalOauth2UserService;

    @Autowired
    JwtService jwtService;

    @RequestMapping(value = "/login/auth", method = RequestMethod.POST)
    public ResponseEntity<?> tokenVerify(String idToken){
        System.out.println("RequestBody value : " + idToken);
        UserDto user =  principalOauth2UserService.tokenVerify(idToken);

        HttpHeaders res = new HttpHeaders();

        if (user.getEmail() != null) {
            String atJWT = jwtService.create();
            System.out.println(atJWT);
            res.add("at-jwt-token", atJWT);
        }




        return ResponseEntity.ok().headers(res).body(user);
    }


}
