package com.ssafy.project.EmotionPlanet.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.EmotionPlanet.Dao.TestDao;
import com.ssafy.project.EmotionPlanet.Dto.TestDto;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestDao testDao;

	List<TestDto> list;
	List<TestDto> happiness = new ArrayList<TestDto>();
	List<TestDto> depressed = new ArrayList<TestDto>();
	List<TestDto> neutrality = new ArrayList<TestDto>();
	List<TestDto> fear = new ArrayList<TestDto>();
	List<TestDto> surprise = new ArrayList<TestDto>();
	List<TestDto> anger = new ArrayList<TestDto>();
	TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
	
	@Override
	public List<TestDto> step1() {
		list = new ArrayList<TestDto>();
		happiness = testDao.select(1);
		depressed = testDao.select(2);
		neutrality = testDao.select(3);
		fear = testDao.select(4);
		surprise = testDao.select(5);
		anger = testDao.select(6);
		random(list, happiness, 4, happiness.size());
		random(list, depressed, 4, depressed.size());
		random(list, neutrality, 4, neutrality.size());
		random(list, fear, 4, fear.size());
		random(list, surprise, 4, surprise.size());
		random(list, anger, 4, anger.size());
    	list.sort((a,b) -> a.getName().compareTo(b.getName()));
		return list;
	}

	// 4 , 6
	public void random(List<TestDto> list, List<TestDto> emt, int n, int m) {
		Random r = new Random();
		int number[] = new int[n];
		for (int i = 0; i < n; i++) {
			number[i] = r.nextInt(m);
			for (int j = 0; j < i; j++) {
				if (number[i] == number[j])
					i--;
			}
		}
		for (int i = 0; i < n; i++) {
			list.add(emt.get(number[i]));
		}
		Arrays.sort(number);
		for (int i = n - 1; i >= 0; i--) {
			emt.remove(number[i]);
		}
	}

	@Override
	public List<TestDto> step2(List<TestDto> testDto) {
		list = new ArrayList<TestDto>();
		int score[] = { 6, 5, 4, 3, 2, 1 };
		int count[] = { 8, 6, 4, 3, 2, 1 };
		map = new TreeMap<Integer, Integer>();
		for (int i = 0; i < testDto.size(); i++) {
			TestDto dto = testDto.get(i);
			map.put(dto.getType(), map.getOrDefault(dto.getType(), 0) + 1 + score[i]);
		}
		ArrayList<Map.Entry<Integer, Integer>> totalscore = new ArrayList<>(map.entrySet());
		totalscore.sort((a, b) -> map.get(b.getKey()) - map.get(a.getKey()));
		System.out.println("첫 번째 결과 : "+ totalscore);
		for (int i = 0; i < totalscore.size(); i++) {
			switch (totalscore.get(i).getKey()) {
			case 1:
				random(list, happiness, count[i], happiness.size());
				break;
			case 2:
				random(list, depressed, count[i], depressed.size());
				break;
			case 3:
				random(list, neutrality, count[i], neutrality.size());
				break;
			case 4:
				random(list, fear, count[i], fear.size());
				break;
			case 5:
				random(list, surprise, count[i], surprise.size());
				break;
			case 6:
				random(list, anger, count[i], anger.size());
				break;
			}
		}
    	list.sort((a,b) -> a.getName().compareTo(b.getName()));
		return list;
	}

	@Override
	public TestDto step3(List<TestDto> testDto) {
		for (int i = 0; i < testDto.size(); i++) {
			TestDto dto = testDto.get(i);
			map.put(dto.getType(), map.getOrDefault(dto.getType(), 0) + 1);
		}
		ArrayList<Map.Entry<Integer, Integer>> totalscore = new ArrayList<>(map.entrySet());
		totalscore.sort((a, b) -> map.get(b.getKey()) - map.get(a.getKey()));
		System.out.println("마지막  결과 : "+ totalscore);
		TestDto dto = new TestDto();
		
		switch (totalscore.get(0).getKey()) {
		case 1:
			dto.setName("행복");
			break;
		case 2:
			dto.setName("우울");
			break;
		case 3:
			dto.setName("중립");
			break;
		case 4:
			dto.setName("공포");
			break;
		case 5:
			dto.setName("놀라움");
			break;
		case 6:
			dto.setName("분노");
			break;
		}
		dto.setNo(totalscore.get(0).getKey());
		
		return dto;
	}
}
