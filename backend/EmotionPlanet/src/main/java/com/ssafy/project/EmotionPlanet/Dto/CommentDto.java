package com.ssafy.project.EmotionPlanet.Dto;

import java.time.LocalDateTime;

public class CommentDto {

    private int no;
    private String desc;
    private int author;
    private LocalDateTime date;

    public CommentDto() {
    }

    public CommentDto(int no, String desc, int author, LocalDateTime date) {
        this.no = no;
        this.desc = desc;
        this.author = author;
        this.date = date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
