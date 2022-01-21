package com.ssafy.project.EmotionPlanet.Dao;

import com.ssafy.project.EmotionPlanet.Dto.FindEmailDto;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public int userRegister(UserDto userDto);
    public int userUpdate(UserDto userDto);
    public UserDto userSelect(int no);
    public int userDelete(int no);
    public int duplicateEmail(String email);
    public int duplicateNickname(String nickname);
    public int duplicateTel(String tel);
    public String findEamil(String tel);
    public UserDto findPw(FindEmailDto findEmailDto);
}
