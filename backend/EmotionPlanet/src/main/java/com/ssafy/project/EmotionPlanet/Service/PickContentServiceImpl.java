package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dao.PickContentDao;
import com.ssafy.project.EmotionPlanet.Dto.PickContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickContentServiceImpl implements PickContentService{

    @Autowired
    PickContentDao pickContentDao;

    @Override
    public List<PickContentDto> list(int pickNo) {

        List<PickContentDto> list = pickContentDao.list(pickNo);
        System.out.println("dao 갯수 -----------" + list.size());
        return list;
    }

    @Override
    public PickContentDto select(int no) {
        return pickContentDao.select(no);
    }

    @Override
    public int delete(int no) {
        return pickContentDao.delete(no);
    }
}
