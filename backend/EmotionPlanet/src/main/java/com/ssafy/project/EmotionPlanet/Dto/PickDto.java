package com.ssafy.project.EmotionPlanet.Dto;

import java.time.LocalDateTime;
import java.util.List;

public class PickDto {

    private int no;
    private int userNo;
    private int tagNo;
    private String name;
    private LocalDateTime date;

    List<PickContentDto> contentsList;

    public PickDto() {
    }

    public PickDto(int no, int userNo, int tagNo, String name, LocalDateTime date) {
        this.no = no;
        this.userNo = userNo;
        this.tagNo = tagNo;
        this.name = name;
        this.date = date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getTagNo() {
        return tagNo;
    }

    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
