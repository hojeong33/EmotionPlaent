package com.ssafy.project.EmotionPlanet.Dao;

import com.ssafy.project.EmotionPlanet.Dto.FeedDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedDao {
    public List<FeedDto> list(int no);
    public List<FeedDto> myList(int no);
    public FeedDto read(int no);
    public int write(FeedDto feedDto);
    public int update(FeedDto feedDto);
    public int delete(int no);

    //map - @param
    public int like(
            @Param("userNo") int userNo,
            @Param("feedNo") int feedNo);
    public int unlike(
            @Param("userNo") int userNo,
            @Param("feedNo")int feedNo);
}
