package com.ssafy.project.EmotionPlanet.Dto;

public class TokenDto {

    private String accessJws;
    private String refreshJws;

    public String getAccessJws() {
        return accessJws;
    }

    public void setAccessJws(String accessJws) {
        this.accessJws = accessJws;
    }

    public String getRefreshJws() {
        return refreshJws;
    }

    public void setRefreshJws(String refreshJws) {
        this.refreshJws = refreshJws;
    }
}
