package com.ssafy.project.EmotionPlanet.Controller;

import javax.servlet.http.HttpSession;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@CrossOrigin(
        origins = "http://localhost:5500",
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })

@RestController // 값자체를 리턴
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")  // post 방식으로 들어옴
    public ResponseEntity<UserDto> login(@RequestBody UserDto dto, HttpSession session) { // 로그인
        UserDto userDto = loginService.login(dto);
        if (userDto != null) {
        	System.out.println("로그인 성공");
            System.out.println(userDto);
        	session.setAttribute("userDto", userDto); // 세션에 회원 정보 저장.
            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        } else {
        	System.out.println("로그인 실패");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "이메일 혹은 비밀번호가 잘못되었습니다.");
        }
    }
}
