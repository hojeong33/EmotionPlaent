package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dto.CommentDto;

import java.util.List;

public interface CommentService {
    public List<CommentDto> list(int no);
    public int write(CommentDto commentDto);
    public int update(CommentDto commentDto);
    public int delete(int no);
}
