package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dto.FeedDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FeedService {
    public List<FeedDto> list(int no);
    public List<FeedDto> myList(int no);
    public FeedDto read(int no);
    public int write(FeedDto feedDto);
    public int update(FeedDto feedDto);
    public int delete(int no);
    public int like(int userNo, int feedNo);
    public int unlike(int userNo, int feedNo);
}
