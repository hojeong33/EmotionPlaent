package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dao.PickDao;
import com.ssafy.project.EmotionPlanet.Dto.PickDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickServiceImpl implements PickService{

    @Autowired
    PickDao pickDao;

    @Override
    public int create(PickDto pickDto) {
        return pickDao.create(pickDto);
    }

    @Override
    public List<PickDto> list(int userNo) {
        return pickDao.list(userNo);
    }

    @Override
    public PickDto select(int no) {
        return pickDao.select(no);
    }

    @Override
    public int update(PickDto pickDto) {
        return pickDao.update(pickDto);
    }

    @Override
    public int delete(int no) {

        return pickDao.delete(no);
    }

    @Override
    public int like(int userNo, int pickNo) {
        return pickDao.like(userNo, pickNo);
    }

    @Override
    public int unlike(int userNo, int pickNo) {
        return pickDao.unlike(userNo, pickNo);
    }
}
