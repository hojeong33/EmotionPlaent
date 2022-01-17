package com.ssafy.project.EmotionPlanet.Controller;

import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(
        origins = "http://localhost:5500",
        allowCredentials = "true",
        allowedHeaders ="*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS }
)
@RestController
public class UserController {
    @Autowired
    UserService userService;

    private static final int SUCCESS = 1;

    @PostMapping(value ="/users") // 회원가입
    public ResponseEntity<Integer> register(@RequestBody UserDto userDto) {
        int result = userService.userRegister(userDto);
        if(result == SUCCESS) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "가입 양식이 올바르지 않습니다.");
        }
    }

    @GetMapping(value ="/users/{no}") // 회원번호로 회원 정보 반환
    public ResponseEntity<UserDto> select(@PathVariable String no) {
        int userNo = Integer.parseInt(no);
        UserDto userDto = userService.userSelect(userNo);
        if(userDto != null) {
            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하는 유저가 없습니다.");
        }
    }

    @GetMapping(value ="/users/checkByEmail/{email}") // 이메일 중복검사
    public ResponseEntity<Integer> duplicateEmail(@PathVariable String email) {
        int result = userService.duplicateEmail(email);
        System.out.println(result);
        if(result == 1) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용중인 이메일 입니다.");
        }
    }

    @GetMapping(value ="/users/checkByNickname/{nickname}") // 닉네임 중복검사
    public ResponseEntity<Integer> duplicateNickname(@PathVariable String nickname) {
        int result = userService.duplicateNickname(nickname);
        if(result == 1) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용중인 닉네임 입니다.");
        }
    }


}
