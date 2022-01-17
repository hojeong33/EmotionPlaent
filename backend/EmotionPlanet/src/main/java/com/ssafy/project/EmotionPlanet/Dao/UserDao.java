package com.ssafy.project.EmotionPlanet.Dao;

import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public int userRegister(UserDto userDto);
    public UserDto userSelect(int no);

    public int duplicateEmail(String email);
    public int duplicateNickname(String nickname);
}
