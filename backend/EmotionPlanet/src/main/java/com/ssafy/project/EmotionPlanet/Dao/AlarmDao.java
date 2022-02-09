package com.ssafy.project.EmotionPlanet.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.EmotionPlanet.Dto.AlarmDto;

@Mapper
public interface AlarmDao {
	public int insertAlram(AlarmDto alarmDto);
	public List<AlarmDto> selectAlram(int no);
	public int deleteAlram(int no);
}
