package com.ssafy.project.EmotionPlanet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.EmotionPlanet.Dao.AlarmDao;
import com.ssafy.project.EmotionPlanet.Dao.UserDao;
import com.ssafy.project.EmotionPlanet.Dto.AlarmDto;

@Service
public class AlarmServiceImpl implements AlarmService {
	
	@Autowired
	AlarmDao alarmdao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public AlarmDto senderSelect(AlarmDto alarmDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
