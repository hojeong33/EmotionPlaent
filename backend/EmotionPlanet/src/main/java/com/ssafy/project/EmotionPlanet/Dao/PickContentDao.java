package com.ssafy.project.EmotionPlanet.Dao;

import com.ssafy.project.EmotionPlanet.Dto.PickContentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PickContentDao {

    public List<PickContentDto> listOnMusic(int pickNo);
    public List<PickContentDto> listOnMovie(int pickNo);
    public List<PickContentDto> listOnActivity(int pickNo);

    public PickContentDto selectOnMusic(int no);
    public PickContentDto selectOnMovie(int no);
    public PickContentDto selectOnActivity(int no);

    public int delete(int no);

}
