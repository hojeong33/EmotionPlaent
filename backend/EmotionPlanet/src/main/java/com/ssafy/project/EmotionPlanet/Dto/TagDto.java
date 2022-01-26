package com.ssafy.project.EmotionPlanet.Dto;

public class TagDto {

    private int no;
    private int feedNo;
    private String name;
    private int type;

    public TagDto() {
    }



    public TagDto(int no, int feedNo, String name, int type) {
        this.no = no;
        this.feedNo = feedNo;
        this.name = name;
        this.type = type;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFeedNo() {
        return feedNo;
    }

    public void setFeedNo(int feedNo) {
        this.feedNo = feedNo;
    }
}
