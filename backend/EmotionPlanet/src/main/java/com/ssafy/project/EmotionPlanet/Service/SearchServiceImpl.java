package com.ssafy.project.EmotionPlanet.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.EmotionPlanet.Dao.SearchDao;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	SearchDao searchDao; 

	@Override
	public List<HashMap<String,Integer>>  tagSelect(String name) {
		List<HashMap<String,Integer>>  list = searchDao.tagSelect(name);
		if(list.size() == 0) {
			return null;			
		}
		else {
			return list;			
		}
	}

	@Override
	public List<HashMap<String, Integer>> tagDetailSelect(String name) {
		List<HashMap<String,Integer>>  list = searchDao.tagDetailSelect(name);
		if(list.size() == 0) {
			return null;			
		}
		else {
			return list;			
		}
	}
}