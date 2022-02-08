package com.ssafy.project.EmotionPlanet.Dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.EmotionPlanet.Dto.AlarmDto;

@Mapper
public interface AlarmDao {
	public AlarmDto senderSelect(AlarmDto alarmDto);
}
