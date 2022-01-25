package com.ssafy.project.EmotionPlanet.Controller;

import java.util.HashMap;
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
import com.ssafy.project.EmotionPlanet.Service.SearchService;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
		RequestMethod.OPTIONS })

@RestController
public class SearchController {
	@Autowired
	SearchService searchService;

	@GetMapping(value = "/serachs/byTag/{name}")  //태그 검색
	public ResponseEntity<List<HashMap<String,Integer>>> tagSelect(@PathVariable String name) {
		List<HashMap<String,Integer>> list = searchService.tagSelect(name);
		if (list != null) {
			System.out.println("태그 검색 성공");
			System.out.println(list);
			return new ResponseEntity<List<HashMap<String,Integer>>>(list, HttpStatus.OK);
		} else {
			System.out.println("태그 검색 실패");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하는 정보가 없습니다.");
		}
	}
	
	@GetMapping(value = "/serachs/byTag/detail/{name}")  //태그 상세 검색
	public ResponseEntity<List<HashMap<String,Integer>>> tagDetailSelect(@PathVariable String name) {
		List<HashMap<String,Integer>> list = searchService.tagDetailSelect(name);
		if (list != null) {
			System.out.println("태그 상세 검색 성공");
			System.out.println(list);
			return new ResponseEntity<List<HashMap<String,Integer>>>(list, HttpStatus.OK);
		} else {
			System.out.println("태그 상세 검색 실패");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하는 정보가 없습니다.");
		}
	}
}