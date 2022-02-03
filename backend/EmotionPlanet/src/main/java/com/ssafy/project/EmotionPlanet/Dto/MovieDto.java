package com.ssafy.project.EmotionPlanet.Dto;

public class MovieDto {
	private int apiId;
	private String title;
	private String descr;
	private String year;
	private String imgLink;
	private String genre;
	public int getApiId() {
		return apiId;
	}
	public void setApiId(int apiId) {
		this.apiId = apiId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImgLink() {
		return imgLink;
	}
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public MovieDto() {}
	public MovieDto(int apiId, String title, String descr, String year, String imgLink, String genre) {
		super();
		this.apiId = apiId;
		this.title = title;
		this.descr = descr;
		this.year = year;
		this.imgLink = imgLink;
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "MovieDto [apiId=" + apiId + ", title=" + title + ", descr=" + descr + ", year=" + year + ", imgLink="
				+ imgLink + ", genre=" + genre + "]";
	}
	
	
}
