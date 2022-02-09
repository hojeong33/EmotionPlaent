package com.ssafy.project.EmotionPlanet.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.EmotionPlanet.Dao.AlarmDao;
import com.ssafy.project.EmotionPlanet.Dao.UserDao;
import com.ssafy.project.EmotionPlanet.Dto.AlarmDto;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;

@Service
public class AlarmServiceImpl implements AlarmService {
	
	@Autowired
	AlarmDao alarmDao;
	
	@Autowired
	UserDao userDao;
	
	 private static final int SUCCESS = 1;
	 private static final int FAIL = 1;
	
	@Override
	public AlarmDto insertAlram(AlarmDto alarmDto) {
		UserDto sender = userDao.userSelect(alarmDto.getSender());
		Date date = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy-MM-dd");
		String nickname = sender.getNickname();
		String profileImg = sender.getProfileImg();
		String time = format1.format(date);
		String time2 = format2.format(date);		
		alarmDto.setSenderImg(profileImg);
		alarmDto.setSenderNickname(nickname);
		alarmDto.setDate(time);
		if(alarmDto.getType() == 1) { // 팔로우
			alarmDto.setMessage(nickname + "님이 회원님을 팔로우 했습니다." + time2);
		}else if(alarmDto.getType() == 2) { // 댓글
			alarmDto.setMessage(nickname + "님이 댓글을 남겼습니다." + time2);
		}else if(alarmDto.getType() == 3) { // 피드 좋아요
			alarmDto.setMessage(nickname + "님이 좋아요를 했습니다." + time2);
		}else if(alarmDto.getType() == 4) { // 픽 좋아요
			alarmDto.setMessage(nickname + "님이 좋아요를 했습니다." + time2);			
		}
		int result = alarmDao.insertAlram(alarmDto);
		if(result == SUCCESS)
			return alarmDto;
		return null;
	}

	@Override
	public List<AlarmDto> selectAlram(int no) {
		List<AlarmDto> list = alarmDao.selectAlram(no);
		if(list.size() != 0)
			return list;
		return null;
	}

	@Override
	public int deleteAlram(int no) {
		int result = alarmDao.deleteAlram(no);
		if(result == SUCCESS)
			return SUCCESS;
		return FAIL;
	}
	
	

}
