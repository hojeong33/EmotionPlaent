package com.ssafy.project.EmotionPlanet.Dto;

import java.util.List;

public class UserRequestDto {

    private int no;
    private String nickname;
    private String profileImg;

    public UserRequestDto() {
    }

    public UserRequestDto(int no, String nickname, String profileImg) {
        this.no = no;
        this.nickname = nickname;
        this.profileImg = profileImg;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

}
