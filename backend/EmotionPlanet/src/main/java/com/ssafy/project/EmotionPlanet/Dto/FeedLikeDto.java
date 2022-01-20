package com.ssafy.project.EmotionPlanet.Dto;

public class FeedLikeDto {

    private int userNo;
    private int feedNo;


    public FeedLikeDto() {
    }

    public FeedLikeDto(int userNo, int feedNo) {
        this.userNo = userNo;
        this.feedNo = feedNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getFeedNo() {
        return feedNo;
    }

    public void setFeedNo(int feedNo) {
        this.feedNo = feedNo;
    }
}
