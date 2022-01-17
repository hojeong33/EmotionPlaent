package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dto.UserDto;

public interface UserService {
    public int userRegister(UserDto dto);
    public UserDto userSelect(int no);
    public int duplicateEmail(String email);
    public int duplicateNickname(String nickname);
}
