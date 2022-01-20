package com.ssafy.project.EmotionPlanet.Service;

import java.util.List;

import com.ssafy.project.EmotionPlanet.Dto.FollowDto;

public interface FollowService {
	public int followRegister(FollowDto followDto);
	public List<FollowDto> followSelect(int no);
	public List<FollowDto> followingSelect(int no);
	public int followDelete(FollowDto followDto);
	public int followUpdate(FollowDto followDto);
}
