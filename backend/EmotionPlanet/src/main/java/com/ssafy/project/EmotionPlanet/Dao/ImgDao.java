package com.ssafy.project.EmotionPlanet.Dao;

import com.ssafy.project.EmotionPlanet.Dto.ImgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImgDao {

    public List<ImgDto> list(int relationNo);

    public ImgDto select(int no);

}
