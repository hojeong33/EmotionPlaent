package com.ssafy.project.EmotionPlanet.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.EmotionPlanet.Dto.TagDto;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;

@Mapper
public interface SearchDao {
	public List<TagDto> tagSelect(String name);
	public List<Integer> tagListSelect(String name);
	public List<UserDto> userSelect(String name);
//	public List<PickDto> recommendSelect(String name);
//	public List<PickContentDto> pickcontentSelect(int no);
}