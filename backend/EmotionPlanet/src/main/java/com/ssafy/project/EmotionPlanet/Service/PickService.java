package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dto.PickDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PickService {

    public int create(PickDto pickDto);

    public List<PickDto> list(int userNo);

    public PickDto select(int no);

    public int update(PickDto pickDto);

    public int delete(int no);

    public int like(int userNo, int pickNo);
    public int unlike(int userNo, int pickNo);

}