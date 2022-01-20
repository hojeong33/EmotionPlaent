package com.ssafy.project.EmotionPlanet.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.EmotionPlanet.Dto.FollowDto;

@Mapper
public interface FollowDao {
	public int followRegister(FollowDto followDto);
	public List<FollowDto> followSelect(int no);
	public List<FollowDto> followingSelect(int no);
	public int followDelete(FollowDto followDto);
	public int followUpdate(FollowDto followDto);
}
