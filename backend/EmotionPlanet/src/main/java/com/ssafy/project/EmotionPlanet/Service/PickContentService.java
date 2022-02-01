package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dto.PickContentDto;

import java.util.List;

public interface PickContentService {

    public List<PickContentDto> list(int pickNo);

    public PickContentDto select(int no);

    public int delete(int no);
}
