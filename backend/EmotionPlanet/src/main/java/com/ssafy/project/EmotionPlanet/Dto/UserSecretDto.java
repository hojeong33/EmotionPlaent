package com.ssafy.project.EmotionPlanet.Dto;

public class UserSecretDto {

    private int no;
    private String email;
    private String nickname;
    private String birth;
    private String profileImg;
    private String tel;
    private int mood;

    public UserSecretDto() {

    }

    public UserSecretDto(int no, String email, String nickname, String birth, String profileImg, String tel, int mood) {
        this.no = no;
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
        this.profileImg = profileImg;
        this.tel = tel;
        this.mood = mood;
    }

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

}
