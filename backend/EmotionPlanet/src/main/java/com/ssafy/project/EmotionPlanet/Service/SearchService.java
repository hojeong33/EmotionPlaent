package com.ssafy.project.EmotionPlanet.Service;

import java.util.HashMap;
import java.util.List;

public interface SearchService {
	public List<HashMap<String,Integer>> tagSelect(String name);
	public List<HashMap<String,Integer>> tagDetailSelect(String name);
}