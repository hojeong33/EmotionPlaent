package com.ssafy.project.EmotionPlanet.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssafy.project.EmotionPlanet.Util.createAccesstoken;

@Service
public class ApiServiceImpl implements ApiService {
	
	private final RestTemplate restTemplate = new RestTemplate();
	String text = "sad";
	private String URL= "https://api.spotify.com/v1/recommendations/?seed_genres="+text+"&limit=100&market=KR";
	
	@Override
	public String Recommendations() {
		
		createAccesstoken create = new createAccesstoken();

		final HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+create.accesstoken());

		final HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange(URL, HttpMethod.GET, entity, String.class).getBody();
	}
}


/*
행복(happy) &max_energy=0.99&min_energy=0.7&max_valence=0.99&min_valence=0.9&max_danceability=0.99&min_danceability=0.7";
우울(sad) &max_energy=0.2&min_energy=0.0&max_valence=0.15&min_valence=0.0&max_danceability=0.3&min_danceability=0.0";

공포(goth|acoustic) &max_energy=0.75&min_energy=0.65&max_valence=0.35&min_valence=0.25&max_danceability=0.75&min_danceability=0.65";

놀람 &max_energy=0.99&min_energy=0.8&max_valence=0.6&min_valence=0.5&max_danceability=0.9&min_danceability=0.8";

분노 &max_energy=0.6&min_energy=0.5&max_valence=0.25&min_valence=0.15&max_danceability=0.6&min_danceability=0.5";

중립(인기도) &max_energy=0.55&min_energy=0.45&max_valence=0.55&min_valence=0.45&max_danceability=0.55&min_danceability=0.45";
*/