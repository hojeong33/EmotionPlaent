package com.ssafy.project.EmotionPlanet.Dto;

public class ActivityDto {
	private String title;
	private String imgLink;
	private int type;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgLink() {
		return imgLink;
	}
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public ActivityDto() {}
	public ActivityDto(String title, String imgLink, int type) {
		super();
		this.title = title;
		this.imgLink = imgLink;
		this.type = type;
	}
	@Override
	public String toString() {
		return "ActivityDto [title=" + title + ", imgLink=" + imgLink + ", type=" + type + "]";
	}
	
}
