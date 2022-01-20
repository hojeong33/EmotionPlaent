package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dao.UserDao;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public int userRegister(UserDto userDto) {
		if (userDao.userRegister(userDto) == SUCCESS) // 성공
			return SUCCESS;
		else // 실패
			return FAIL;
	}

	@Override
	public UserDto userSelect(int no) {
		UserDto dto = null;
		dto = userDao.userSelect(no);
		return dto;
	}

	@Override
	public int duplicateEmail(String email) {
		if (userDao.duplicateEmail(email) == 0) // 사용가능
			return SUCCESS;
		else // 중복
			return FAIL;
	}

	@Override
	public int duplicateNickname(String nickname) {
		if (userDao.duplicateNickname(nickname) == 0) // 사용가능
			return SUCCESS;
		else // 중복
			return FAIL;
	}

	@Override
	public int userDelete(int no) {
		if (userDao.userDelete(no) == SUCCESS) 
			return SUCCESS;
		else // 실패
			return FAIL;
	}

	@Override
	public int userUpdate(UserDto userDto, UserDto changeuserDto) {
		//변경되는 유정 정보가 넘어왔을때 null또는 0값이 아닌 값 즉 변경되는 값을 찾아 기존 유저정보를 변경함
		if (changeuserDto.getMood() != 0) { // 감정 정보 변경
			userDto.setMood(changeuserDto.getMood());
		} else if (changeuserDto.getProfileImg() != null) { // 프로필 변경 프로필 사진 받아오는것도 생각 해야함
			userDto.setProfileImg(changeuserDto.getProfileImg());
		} else if (changeuserDto.getPw() != null) { // 비밀 번호 변경
			userDto.setPw(changeuserDto.getPw());
		} else if (changeuserDto.getNickname() != null) { // 활동명 변경
			userDto.setNickname(changeuserDto.getNickname());
		} else { // 계정 공개 비공개
			userDto.setPublish(changeuserDto.isPublish());
		}
		if (userDao.userUpdate(userDto) == SUCCESS) // 변경된 기존 유저정보를 가지고 db내용을 변경함
			return SUCCESS;
		else // 실패
			return FAIL;
	}
}