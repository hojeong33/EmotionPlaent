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
public class PickContentServiceImpl implements PickContentService{

    @Autowired
    PickContentDao pickContentDao;

    @Autowired
    PickDao pickDao;

    @Override
    public List<PickContentDto> list(int pickNo) {

        List<PickContentDto> list = new ArrayList<>();
        PickDto pickDto = pickDao.select(pickNo);
        int type = pickDto.getType();

        if (type == 0) {

        } else if (type == 1) {

        } else {

        }
        System.out.println("dao 갯수 -----------" + list.size());
        return list;
    }

    @Override
    public PickContentDto select(int no) {
        return null;
    }

    @Override
    public int delete(int no) {
        return pickContentDao.delete(no);
    }
}
