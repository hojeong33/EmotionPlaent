package com.ssafy.project.EmotionPlanet.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.ssafy.project.EmotionPlanet.Dto.FeedDto;
import com.ssafy.project.EmotionPlanet.Dto.PickDto;
import com.ssafy.project.EmotionPlanet.Dto.TagDto;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Service.SearchService;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
		RequestMethod.OPTIONS })

@RestController
public class SearchController {
	@Autowired
	SearchService searchService;

	@GetMapping(value = "/serachs/byTag/{name}")  //태그 검색
	public ResponseEntity<List<TagDto>> tagSelect(@PathVariable String name) {
		List<TagDto> list = searchService.tagSelect(name);
		if (list != null) {
			System.out.println("태그 검색 성공");
			System.out.println(list);
			return new ResponseEntity<List<TagDto>>(list, HttpStatus.OK);
		} else {
			System.out.println("태그 검색 실패");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하는 정보가 없습니다.");
		}
	}
	
	@GetMapping(value = "/serachs/byTag/list/{name}")  //태그 리스트 검색
	public ResponseEntity<List<FeedDto>> tagListSelect(@PathVariable String name) {
		List<FeedDto> list = searchService.tagListSelect(name);
		if (list != null) {
			System.out.println("태그 상세 검색 성공");
			System.out.println(list);
			return new ResponseEntity<List<FeedDto>>(list, HttpStatus.OK);
		} else {
			System.out.println("태그 상세 검색 실패");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하는 정보가 없습니다.");
		}
	}
	
	@GetMapping(value = "/serachs/byNickName/{name}")  //유저 검색
	public ResponseEntity<List<UserDto>> userSelect(@PathVariable String name) {
		List<UserDto> list = searchService.userSelect(name);
		if (list != null) {
			System.out.println("유저 검색 성공");
			System.out.println(list);
			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
		} else {
			System.out.println("유저 검색 실패");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하는 정보가 없습니다.");
		}
	}
	
	@GetMapping(value = "/serachs/byRecommend/{name}")  //찜목록 태그 검색
	public ResponseEntity<List<PickDto>> recommendSelect(@PathVariable String name) {
		List<PickDto> list = searchService.recommendSelect(name);
		if (list != null) {
			System.out.println("찜목록 검색 성공");
			System.out.println(list);
			return new ResponseEntity<List<PickDto>>(list, HttpStatus.OK);
		} else {
			System.out.println("찜목록 검색 실패");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하는 정보가 없습니다.");
		}
	}
}