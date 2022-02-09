package com.ssafy.project.EmotionPlanet.Dto;

import java.time.LocalDateTime;
import java.util.List;

public class FeedDto {

    private int no;
    private String descr; //내용
    private int author;
    private List<TagDto> tags;
    private LocalDateTime date;

    private List<CommentDto> comments;
    private List<LikeDto> likes;
    private List<ImgDto> imgs;

    private UserRequestDto authorDetail;
    private boolean like = false;
    private boolean owner = false;


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<LikeDto> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeDto> likes) {
        this.likes = likes;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public List<ImgDto> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgDto> imgs) {
        this.imgs = imgs;
    }

    public UserRequestDto getAuthorDetail() {
        return authorDetail;
    }

    public void setAuthorDetail(UserRequestDto authorDetail) {
        this.authorDetail = authorDetail;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
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
