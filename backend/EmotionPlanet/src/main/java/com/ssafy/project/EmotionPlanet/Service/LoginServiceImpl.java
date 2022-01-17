package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dao.LoginDao;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;

    @Override
    public UserDto login(UserDto dto) { // 로그인 처리
        UserDto userDto = loginDao.login(dto.getEmail());
        System.out.println("loginService: " + userDto);
        if(userDto != null && userDto.getPw().equals(dto.getPw())) {
            return userDto;
        }else {
            return null;
        }
    }

}
