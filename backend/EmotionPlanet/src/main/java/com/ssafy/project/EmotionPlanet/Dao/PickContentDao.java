package com.ssafy.project.EmotionPlanet.Dao;

import com.ssafy.project.EmotionPlanet.Dto.PickContentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PickContentDao {

    public List<PickContentDto> list(int pickNo);

    public PickContentDto select(int no);

    public int delete(int no);

}
