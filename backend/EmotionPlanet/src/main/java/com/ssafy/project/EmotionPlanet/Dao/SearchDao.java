package com.ssafy.project.EmotionPlanet.Dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchDao {
	public List<HashMap<String,Integer>> tagSelect(String name);
	public List<HashMap<String,Integer>> tagDetailSelect(String name);
}