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
        return pickDtos;
    }

    @Override
    public List<Integer> listOnNo(int userNo) {

        List<PickDto> pickDtos = pickDao.list(userNo);
        List<Integer> picksNo = new ArrayList<>();

        for (PickDto pickDto : pickDtos) {
            picksNo.add(pickDto.getNo());
        }
        return picksNo;
    }

    @Override
    public PickDto select(int no, int userNo) {
        PickDto pickDto = pickDao.select(no);

        int like = pickDao.liking(userNo, pickDto.getNo());
        if(like != 0) pickDto.setLike(true);
        else pickDto.setLike(false);

        if(userNo == pickDto.getUserNo()) pickDto.setOwner(true);
        else pickDto.setOwner(false);

        int likes = pickDao.likes(pickDto.getNo());
        pickDto.setLikes(likes);

        int type = pickDto.getType();

        List<Integer> contentListNo = new ArrayList<>();
        if (type == 0) {
            List<PickContentDto> pickContentDtos = pickContentDao.listOnMusic(no);
            for (PickContentDto pickContentDto : pickContentDtos) {
                contentListNo.add(pickContentDto.getNo());
            }
        } else if (type == 1) {
            List<PickContentDto> pickContentDtos = pickContentDao.listOnMovie(no);
            for (PickContentDto pickContentDto : pickContentDtos) {
                contentListNo.add(pickContentDto.getNo());
            }
        } else {
            List<PickContentDto> pickContentDtos = pickContentDao.listOnActivity(no);
            for (PickContentDto pickContentDto : pickContentDtos) {
                contentListNo.add(pickContentDto.getNo());
            }
        }

        pickDto.setContentsList(contentListNo);
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
