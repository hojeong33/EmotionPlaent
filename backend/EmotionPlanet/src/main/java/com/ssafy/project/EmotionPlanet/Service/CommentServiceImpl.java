package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dao.CommentDao;
import com.ssafy.project.EmotionPlanet.Dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDao commentDao;

    @Override
    public List<CommentDto> list(int no) {
        return commentDao.list(no);
    }

    @Override
    public int write(CommentDto commentDto) {
        return commentDao.write(commentDto);
    }

    @Override
    public int update(CommentDto commentDto) {
        return commentDao.update(commentDto);
    }

    @Override
    public int delete(int no) {
        return commentDao.delete(no);
    }
}
