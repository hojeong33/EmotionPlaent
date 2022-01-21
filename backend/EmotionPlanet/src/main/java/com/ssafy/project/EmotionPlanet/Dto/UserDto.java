package com.ssafy.project.EmotionPlanet.Dto;

import java.util.Date;

public class UserDto {

    private int no;
    private String email;
    private String pw;
    private String nickname;
    private String birth;
    private String profileImg;
    private int mood;
    private boolean publish;
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getProfileImg() {
        return profileImg;
    }
    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
    public int getMood() {
        return mood;
    }
    public void setMood(int mood) {
        this.mood = mood;
    }
    public boolean isPublish() {
        return publish;
    }
    public void setPublish(boolean publish) {
        this.publish = publish;
    }
    
    public UserDto() {}
    public UserDto(int no, String email, String pw, String nickname, String birth, String profileImg, int mood,
            boolean publish) {
        super();
        this.no = no;
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
        this.birth = birth;
        this.profileImg = profileImg;
        this.mood = mood;
        this.publish = publish;
    }
    @Override
    public String toString() {
        return "UserDto [no=" + no + ", email=" + email + ", pw=" + pw + ", nickname=" + nickname + ", birth=" + birth
                + ", profileImg=" + profileImg + ", mood=" + mood + ", publish=" + publish + "]";
    }

    
}