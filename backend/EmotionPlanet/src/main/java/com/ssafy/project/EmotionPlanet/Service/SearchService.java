package com.ssafy.project.EmotionPlanet.Service;

import java.util.List;

import com.ssafy.project.EmotionPlanet.Dto.FeedDto;
import com.ssafy.project.EmotionPlanet.Dto.PickDto;
import com.ssafy.project.EmotionPlanet.Dto.TagDto;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;

public interface SearchService {
	public List<TagDto> tagSelect(String name);
	public List<FeedDto> tagListSelect(String name);
	public List<UserDto> userSelect(String name);
	public List<PickDto> recommendSelect(String name);
}