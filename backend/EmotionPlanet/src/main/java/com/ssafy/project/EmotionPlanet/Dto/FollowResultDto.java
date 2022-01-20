package com.ssafy.project.EmotionPlanet.Dto;

import java.util.List;

public class FollowResultDto {
	private List<FollowDto> list;

	public List<FollowDto> getList() {
		return list;
	}

	public void setList(List<FollowDto> list) {
		this.list = list;
	}

	public FollowResultDto() {}
	public FollowResultDto(List<FollowDto> list) {
		super();
		this.list = list;
	}

	@Override
	public String toString() {
		return "FollowResultDto [list=" + list + "]";
	}
	
}
