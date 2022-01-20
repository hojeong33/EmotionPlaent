package com.ssafy.project.EmotionPlanet.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.project.EmotionPlanet.Dto.ApiDto;
import com.ssafy.project.EmotionPlanet.Service.ApiServiceImpl;
import com.ssafy.project.EmotionPlanet.Util.createAccesstoken;

import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
		RequestMethod.OPTIONS })

@RestController
public class ApiController {
	
	@Autowired
	ApiServiceImpl apiservice;
	private final RestTemplate restTemplate = new RestTemplate();
	private final String URL = "https://api.spotify.com/v1/audio-features/{id}";
	
	@GetMapping(value = "/api/{id}")
	public ApiDto AudioFeatures(@PathVariable String id) {
		createAccesstoken create = new createAccesstoken();

		final HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+create.accesstoken());


		final HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange(URL, HttpMethod.GET, entity, ApiDto.class, id).getBody();
	}
	
	@GetMapping(value = "/api2")
	public String Recommendations() {
		return apiservice.Recommendations();
	}
}

// 11dFghVXANMlKmJXsNCbNl
