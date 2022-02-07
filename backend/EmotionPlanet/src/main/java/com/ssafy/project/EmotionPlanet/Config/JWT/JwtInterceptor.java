package com.ssafy.project.EmotionPlanet.Config.JWT;

import com.google.gson.Gson;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Dto.UserRequestDto;
import com.ssafy.project.EmotionPlanet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("####### Interceptor preHandle Start!!!");

        String atJwtToken = request.getHeader("at-jwt-access-token");
        String atJwtRefreshToken = request.getHeader("at-jwt-refresh-token");
 
        System.out.println("at-jwt-access-token : " + atJwtToken);
        System.out.println("request method : " + request.getMethod());

        if ("OPTIONS".equals(request.getMethod())) {
            System.out.println("request method is OPTIONS!!");
            return true;
        }

        if(atJwtRefreshToken == null) {
            if(atJwtToken != null && atJwtToken.length() > 0) {
                if(jwtService.validate(atJwtToken)) return true;
                else throw new IllegalArgumentException("Access Token Error!!!");
            }else {
                throw new IllegalArgumentException("No Access Token!!!");
            }
        }else {
            System.out.println("check : pass" );
            if(jwtService.validate(atJwtRefreshToken)) {
                String accessTokenDecode = jwtService.decode(atJwtToken);
                Gson gson = new Gson();
                UserRequestDto jwtPayload = gson.fromJson(accessTokenDecode, UserRequestDto.class);

                String refreshTokenInDBMS = userService.selectRefreshToken(jwtPayload.getUserInfo().getEmail());

                if(refreshTokenInDBMS.equals(atJwtRefreshToken)) {
                    // 3. recreate access token
                    // ???
                    System.out.println("일치합니다!!!");
                    String accessJws = jwtService.createJws(30, jwtPayload.getUserInfo());
                    response.addHeader("at-jwt-access-token", accessJws);
                }else {
                    throw new IllegalArgumentException("Refresh Token Error!!!");
                }
                return true;
            }else {
                throw new IllegalArgumentException("Refresh Token Error!!!");
            }
        }

//        String res = jwtService.decode(atJwtToken);
//        System.out.println("res : " + res);
//        Gson gsons = new Gson();
//        UserRequestDto a = gsons.fromJson(res, UserRequestDto.class);
//        System.out.println("a : " + a.toString());
//
//        String rt = userService.selectRefreshToken(a.getUserInfo().getEmail());
//        System.out.println("rt : " + rt);
//
//        if(rt.equals(atJwtRefreshToken)) {
//            System.out.println("일치합니다!!!");
//        }else {
//            System.out.println("일치하지않습니다!!!");
//            throw new IllegalArgumentException("Refresh Token Error!!!")
//        }

//        if ("OPTIONS".equals(request.getMethod())) {
//            System.out.println("request method is OPTIONS!!");
//            return true;
//        }

//        if(atJwtToken != null && atJwtToken.length() > 0) {
//            if(jwtService.validate(atJwtToken)) return true;
//            else throw new IllegalArgumentException("Token Error!!!");
//        }else {
//            throw new IllegalArgumentException("No Token!!!");
//        }
    }

}