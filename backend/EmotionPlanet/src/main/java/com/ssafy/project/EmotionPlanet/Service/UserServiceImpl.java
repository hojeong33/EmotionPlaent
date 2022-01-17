package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dao.UserDao;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Override
    public int userRegister(UserDto userDto) {

        int result = 0;
        if (userDao.userRegister(userDto) == SUCCESS) { // 성공
            result = SUCCESS;
        }else { // 실패
            result = FAIL;
        }

        return result;
    }
    @Override
    public UserDto userSelect(int no) {

        UserDto dto = null;
        dto = userDao.userSelect(no);

        return dto;
    }

    @Override
    public int duplicateEmail(String email) {
        int result = 0;
        if (userDao.duplicateEmail(email) == 0) { // 사용가능
            result = SUCCESS;
        }else { // 중복
            result = FAIL;
        }

        return result;
    }

    @Override
    public int duplicateNickname(String nickname) {
        int result = 0;
        if (userDao.duplicateNickname(nickname) == 0) { // 사용가능
            result = SUCCESS;
        }else { // 중복
            result = FAIL;
        }

        return result;
    }
}
