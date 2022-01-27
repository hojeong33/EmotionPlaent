package com.ssafy.project.EmotionPlanet.Dto;

import java.util.List;

public class PickDto {
	private int no;
	private int userNo;
	private String nickname; // 찜록록 작성자 닉네임
	private String name;
	private String date;
	private int tagNo;
	
	private List<PickContentDto> pickcontents;
	
	public List<PickContentDto> getPickcontents() {
		return pickcontents;
	}
	public void setPickcontents(List<PickContentDto> pickcontents) {
		this.pickcontents = pickcontents;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTagNo() {
		return tagNo;
	}
	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}
	public PickDto() {}
	public PickDto(int no, int userNo, String nickname, String name, String date, int tagNo, List<PickContentDto> pickcontents) {
		super();
		this.no = no;
		this.userNo = userNo;
		this.nickname = nickname;
		this.name = name;
		this.date = date;
		this.tagNo = tagNo;
		this.pickcontents = pickcontents;
	}
	@Override
	public String toString() {
		return "PickDto [no=" + no + ", userNo=" + userNo + ", nickname=" + nickname + ", name=" + name + ", date="
				+ date + ", tagNo=" + tagNo + ", pickcontents=" + pickcontents + "]";
	}
}