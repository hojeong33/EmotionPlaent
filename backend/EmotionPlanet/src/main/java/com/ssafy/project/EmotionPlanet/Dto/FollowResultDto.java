package com.ssafy.project.EmotionPlanet.Dto;

import java.util.List;

public class FollowResultDto {
	private int followResult; // 0이면 언팔 1이면 팔로우중
	private int waition; // 1이면 대기중 
	private List<FollowDto> follower;
	private List<FollowDto> following;
	public int getFollowResult() {
		return followResult;
	}
	public void setFollowResult(int followResult) {
		this.followResult = followResult;
	}
	public int getWaition() {
		return waition;
	}
	public void setWaition(int waition) {
		this.waition = waition;
	}
	public List<FollowDto> getFollower() {
		return follower;
	}
	public void setFollower(List<FollowDto> follower) {
		this.follower = follower;
	}
	public List<FollowDto> getFollowing() {
		return following;
	}
	public void setFollowing(List<FollowDto> following) {
		this.following = following;
	}
	public FollowResultDto() {}
	public FollowResultDto(int followResult, int waition, List<FollowDto> follower, List<FollowDto> following) {
		super();
		this.followResult = followResult;
		this.waition = waition;
		this.follower = follower;
		this.following = following;
	}
	@Override
	public String toString() {
		return "FollowResultDto [followResult=" + followResult + ", waition=" + waition + ", follower=" + follower
				+ ", following=" + following + "]";
	}
	
}
