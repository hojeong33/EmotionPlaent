package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dto.FindEmailDto;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;

public interface UserService {
    public int userRegister(UserDto dto);
    public UserDto userSelect(int no);
    public int userDelete(int no);
    public int duplicateEmail(String email);
    public int duplicateNickname(String nickname);
    public int userUpdate(UserDto userDto, UserDto changuserDto);
    public int duplicateTel(String tel);
    public String findEamil(String tel);
    public String findPw(FindEmailDto findEmailDto);
}