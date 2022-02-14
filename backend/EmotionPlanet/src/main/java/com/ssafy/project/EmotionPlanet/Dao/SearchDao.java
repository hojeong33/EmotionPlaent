package com.ssafy.project.EmotionPlanet.Dao;

import java.util.List;

import com.ssafy.project.EmotionPlanet.Dto.FeedDto;
import com.ssafy.project.EmotionPlanet.Dto.PickDto;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.EmotionPlanet.Dto.TagDto;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;

@Mapper
public interface SearchDao {
	public List<TagDto> tagSelect(String name);
	public List<Integer> tagListSelect(String name);
	public List<UserDto> userSelect(String name);
	public List<FeedDto> feedSelect(String name); // 태그명
	public List<PickDto> pickSelect(String name); // 목록 제목
	
//	public List<PickDto> recommendSelect(String name);
//	public List<PickContentDto> pickcontentSelect(int no);
}