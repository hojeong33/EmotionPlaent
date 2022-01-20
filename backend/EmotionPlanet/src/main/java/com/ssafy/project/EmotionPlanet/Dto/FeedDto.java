package com.ssafy.project.EmotionPlanet.Dto;

import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;

public class FeedDto {

    private int no;
    private String desc;
    private int author;
    private LocalDateTime date;

    private List<CommentDto> comments;

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

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public FeedDto() {
    }

    public FeedDto(int no, String desc, int author, LocalDateTime date) {
        this.no = no;
        this.desc = desc;
        this.author = author;
        this.date = date;
    }



    @Override
    public String toString() {
        return "FeedDto{" +
                "no=" + no +
                ", desc='" + desc + '\'' +
                ", author=" + author +
                ", date=" + date +
                '}';
    }
}
