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
        int result = commentDao.write(commentDto);

        if(result == 1) {
            int commentNo = commentDto.getNo();
            int feedNo = commentDto.getFeedNo();
            return commentDao.relation(commentNo, feedNo);
        } else return result;
    }

    @Override
    public int update(CommentDto commentDto) {
        return commentDao.update(commentDto);
    }

    @Override
    public int delete(int no) {
        return commentDao.delete(no);
    }

    @Override
    public int relation(int commentNo, int feedNo) {
        return commentDao.relation(commentNo, feedNo);
    }
}
