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
    public List<CommentDto> list(int feedNo) {
        return commentDao.list(feedNo);
    }

    @Override
    public CommentDto read(int no, int userNo) {
        CommentDto commentDto = commentDao.read(no);
        if(userNo == commentDto.getAuthor()) commentDto.setOwner(true);
        else commentDto.setOwner(false);
        return commentDto;
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

}
