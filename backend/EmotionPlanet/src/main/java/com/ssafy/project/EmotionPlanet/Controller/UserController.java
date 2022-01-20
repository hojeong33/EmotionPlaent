package com.ssafy.project.EmotionPlanet.Controller;

import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
		RequestMethod.OPTIONS })
@RestController
public class UserController {
	@Autowired
	UserService userService;

	private static final int SUCCESS = 1;

	@PostMapping(value = "/users") // 회원가입
	public ResponseEntity<Integer> register(@RequestBody UserDto userDto) {
		System.out.println(userDto);
		if (userService.userRegister(userDto) == SUCCESS) {
			return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "가입 양식이 올바르지 않습니다.");
		}
	}

	@GetMapping(value = "/users/{no}") // 회원번호로 회원 정보 반환
	public ResponseEntity<UserDto> select(@PathVariable String no) {
		int userNo = Integer.parseInt(no);
		UserDto userDto = userService.userSelect(userNo);
		if (userDto != null) {
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하는 유저가 없습니다.");
		}
	}

	@DeleteMapping(value = "/users/{no}")
	public ResponseEntity<Integer> delete(@PathVariable String no) { //회원 탈퇴 회원 번호로 회원 정보 삭제
		int userNo = Integer.parseInt(no);
		if (userService.userDelete(userNo) == SUCCESS) {
			return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하는 유저가 없습니다.");
		}
	}
	
	@PutMapping(value ="/users")
	public ResponseEntity<Integer> update(@RequestBody UserDto changeuserDto) { //restapi를 이용해서 http 상태코드를 성공 실패여부로 같이 넘겨준다.
		// 컨트롤에서 서비스 업데이트 호출하면 이때 user dto 두개 넘겨줌
		
		UserDto userDto = userService.userSelect(changeuserDto.getNo()); //입력받은 유저 번호로 기존 유저 정보 가져옴	
		 // 기존 유저 정보와 변경되는 유저 정보를 파라미터로 넘김
		System.out.println(changeuserDto);
		System.out.println(userDto);
		if(userService.userUpdate(userDto, changeuserDto) == SUCCESS) {
			return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
		}else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정할 내용을 확인해 주세요.");
		}
	}

	@GetMapping(value = "/users/checkByEmail/{email}") // 이메일 중복검사
	public ResponseEntity<Integer> duplicateEmail(@PathVariable String email) {
		if (userService.duplicateEmail(email) == SUCCESS) {
			return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용중인 이메일 입니다.");
		}
	}

	@GetMapping(value = "/users/checkByNickname/{nickname}") // 닉네임 중복검사
	public ResponseEntity<Integer> duplicateNickname(@PathVariable String nickname) {
		if (userService.duplicateNickname(nickname) == SUCCESS) {
			return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용중인 닉네임 입니다.");
		}
	}
}