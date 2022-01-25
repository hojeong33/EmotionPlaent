package com.ssafy.project.EmotionPlanet.Dto;

import java.time.LocalDateTime;
import java.util.List;

public class FeedDto {

    private int no;
    private String descr;
    private int author;
    private LocalDateTime date;

    private List<CommentDto> comments;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getdescr() {
        return descr;
    }

    public void setdescr(String descr) {
        this.descr = descr;
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

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public FeedDto() {
    }

    public FeedDto(int no, String descr, int author, LocalDateTime date) {
        this.no = no;
        this.descr = descr;
        this.author = author;
        this.date = date;
    }



    @Override
    public String toString() {
        return "FeedDto{" +
                "no=" + no +
                ", descr='" + descr + '\'' +
                ", author=" + author +
                ", date=" + date +
                '}';
    }
}
