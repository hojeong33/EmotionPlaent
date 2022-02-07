package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dao.UserDao;
import com.ssafy.project.EmotionPlanet.Dto.FindEmailDto;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Dto.UserSecretDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public int userRegister(UserDto userDto) { // 회원 가입
		if (userDao.userRegister(userDto) == SUCCESS) // 성공
			return SUCCESS;
		else // 실패
			return FAIL;
	}

	@Override
	public UserDto userSelect(int no) { // 회원 검색
		return userDao.userSelect(no);
	}

	@Override
	public UserDto userSelectByEmail(String email) {
		return userDao.userSelectByEmail(email);
	}

	@Override
	public int duplicateEmail(String email) { // 이메일 중복 체크
		if (userDao.duplicateEmail(email) == 0) // 사용가능
			return SUCCESS;
		else // 중복
			return FAIL;
	}

	@Override
	public int duplicateNickname(String nickname) { // 닉네임 중복 체크
		if (userDao.duplicateNickname(nickname) == 0) // 사용가능
			return SUCCESS;
		else // 중복
			return FAIL;
	}

	@Override
	public int userDelete(int no) { // 회원 삭제
		if (userDao.userDelete(no) == SUCCESS) 
			return SUCCESS;
		else // 실패
			return FAIL;
	}

	@Override
	public int userUpdate(UserDto userDto, UserDto changeuserDto) { // 회원 수정
		//변경되는 유정 정보가 넘어왔을때 null또는 0값이 아닌 값 즉 변경되는 값을 찾아 기존 유저정보를 변경함
		if (changeuserDto.getMood() != 0) { // 감정 정보 변경
			userDto.setMood(changeuserDto.getMood());
		} else if (changeuserDto.getPw() != null) { // 비밀 번호 변경
			userDto.setPw(changeuserDto.getPw());
		} else if (changeuserDto.getNickname() != null) { // 활동명 변경
			userDto.setNickname(changeuserDto.getNickname());
		} else { // 계정 공개 비공개
			userDto.setPublish(changeuserDto.getPublish());
		}
		if (userDao.userUpdate(userDto) == SUCCESS) // 변경된 기존 유저정보를 가지고 db내용을 변경함
			return SUCCESS;
		else // 실패
			return FAIL;
	}

	@Override
	public int duplicateTel(String tel) { // 전화번호 중복검사
		if (userDao.duplicateTel(tel) == 0) // 사용가능
			return SUCCESS;
		else // 중복
			return FAIL;
	}

	@Override
	public String findEamil(String tel) { // 이메일 찾기
		return userDao.findEamil(tel);
	}

	@Override
	public String findPw(FindEmailDto findEmailDto) { // 비밀번호 찾기
		UserDto userDto = userDao.findPw(findEmailDto);
		if(userDto != null) {
			String Pw = randomPassword(); //랜덤 비밀번호 생성
			userDto.setPw(Pw); // dto에 생성된 비밀번호 저장
			if(userDao.userUpdate(userDto) == SUCCESS) { // 변경된 비밀번호로 회원 정보 갱신
				return userDto.getPw(); // 생성된 비밀번호 리턴
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	@Override
	public int userRefreshToken(UserDto userDto) {
		return userDao.userRefreshToken(userDto);
	}

	@Override
	public String selectRefreshToken(String email) {
		return userDao.selectRefreshToken(email);
	}

	public String randomPassword(){ //랜덤 비밀번호 생성 메서드
	        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
	                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	        String str = "";
	        int idx = 0;
	        for (int i = 0; i < 10; i++) {
	            idx = (int) (charSet.length * Math.random());
	            str += charSet[idx];
	        }
	        return str;
	    }
}