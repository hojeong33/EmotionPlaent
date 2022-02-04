package com.ssafy.project.EmotionPlanet.Config.auth;

import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("login")
// 로그인 요청이 오면 자동으로 userDetailService 타입으로 ioc 되어있는 loadserByUserName 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    // 시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userEntity = userService.userSelectByEmail(email);

        if (userEntity != null) {
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
