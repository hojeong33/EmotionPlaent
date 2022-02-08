package com.ssafy.project.EmotionPlanet.Dto;

public class AlarmDto {
	private int no;
	private int sender;
	private String senderNickname;
	private String senderImg;
	private int receiver;
	private int feedno;
	private int commentno;
	private String date;
	private int type;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public String getSenderNickname() {
		return senderNickname;
	}
	public void setSenderNickname(String senderNickname) {
		this.senderNickname = senderNickname;
	}
	public String getSenderImg() {
		return senderImg;
	}
	public void setSenderImg(String senderImg) {
		this.senderImg = senderImg;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public int getFeedno() {
		return feedno;
	}
	public void setFeedno(int feedno) {
		this.feedno = feedno;
	}
	public int getCommentno() {
		return commentno;
	}
	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public AlarmDto() {}
	public AlarmDto(int no, int sender, String senderNickname, String senderImg, int receiver, int feedno,
			int commentno, String date, int type) {
		super();
		this.no = no;
		this.sender = sender;
		this.senderNickname = senderNickname;
		this.senderImg = senderImg;
		this.receiver = receiver;
		this.feedno = feedno;
		this.commentno = commentno;
		this.date = date;
		this.type = type;
	}
	@Override
	public String toString() {
		return "AlarmDto [no=" + no + ", sender=" + sender + ", senderNickname=" + senderNickname + ", senderImg="
				+ senderImg + ", receiver=" + receiver + ", feedno=" + feedno + ", commentno=" + commentno + ", date="
				+ date + ", type=" + type + "]";
	}
}
