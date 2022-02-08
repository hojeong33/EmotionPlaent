package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dao.PickContentDao;
import com.ssafy.project.EmotionPlanet.Dao.PickDao;
import com.ssafy.project.EmotionPlanet.Dto.PickContentDto;
import com.ssafy.project.EmotionPlanet.Dto.PickDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PickServiceImpl implements PickService{

    @Autowired
    PickDao pickDao;

    @Autowired
    PickContentDao pickContentDao;

    @Override
    public int create(PickDto pickDto) {
        return pickDao.create(pickDto);
    }

    @Override
    public List<PickDto> list(int userNo) {

        List<PickDto> pickDtos = pickDao.list(userNo);

        for (PickDto pickDto : pickDtos) {
            if(userNo == pickDto.getUserNo()) pickDto.setOwner(true);
            else pickDto.setOwner(false);
            //pickDto.setContentsList(pickContentDao.list(pickDto.getNo()));
        }

        return pickDtos;
    }

    @Override
    public PickDto select(int no, int userNo) {
        PickDto pickDto = pickDao.select(no);
        if(userNo == pickDto.getUserNo()) pickDto.setOwner(true);
        else pickDto.setOwner(false);
        //pickDto.setContentsList(pickContentDao.list(pickDto.getNo()));
        return pickDto;
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
