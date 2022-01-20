package com.ssafy.project.EmotionPlanet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.ssafy.project.EmotionPlanet.Dto.FollowDto;
import com.ssafy.project.EmotionPlanet.Service.FollowService;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
		RequestMethod.OPTIONS })
@RestController
public class FollowController {
	@Autowired
	FollowService followService;

	private static final int SUCCESS = 1;

	@PostMapping(value = "/follows")
	public ResponseEntity<Integer> register(@RequestBody FollowDto followDto) {
		if (followService.followRegister(followDto) == SUCCESS) {
			return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "다시 추가 해주세요.");
		}
	}
	
	//이사람이랑 팔로우중인지 확인하는거 하나 필요  @DeleteMapping(value = {"/friends/{userId}/{friendId}"})
	
	@GetMapping(value = "/follows/{no}")
	public ResponseEntity<List<FollowDto>> followselect(@PathVariable String no) {
		int userNo = Integer.parseInt(no);
		List<FollowDto> list = followService.followSelect(userNo);
		System.out.println(list);
		if(list != null) {
			return new ResponseEntity<List<FollowDto>>(list, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "팔로워 중인 사람이 없습니다.");
		}
	}
 
	@GetMapping(value = "/followings/{no}")
	public ResponseEntity<List<FollowDto>> followingselect(@PathVariable String no) {
		int userNo = Integer.parseInt(no);
		List<FollowDto> list = followService.followingSelect(userNo);
		if(list != null) {
			return new ResponseEntity<List<FollowDto>>(list, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "팔로잉 중인 사람이 없습니다.");
		}
	}

	@DeleteMapping(value = "/follows")
	public ResponseEntity<Integer> delete(@RequestBody FollowDto followDto) {
		if(followService.followDelete(followDto) == SUCCESS) {
			return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "다시 삭제 해주세요.");
		}
	}

	@PutMapping(value = "/follows")
	public ResponseEntity<Integer> update(@RequestBody FollowDto followDto) {
		if(followService.followDelete(followDto) == SUCCESS) {
			return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "다시 수락 해주세요.");
		}
	}
}