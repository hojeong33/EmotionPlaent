package com.ssafy.project.EmotionPlanet.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.project.EmotionPlanet.Dao.FollowDao;
import com.ssafy.project.EmotionPlanet.Dto.FollowDto;
import com.ssafy.project.EmotionPlanet.Dto.FollowResultDto;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowDao followDao;
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public int followRegister(FollowDto followDto) {
		if(followDao.followRegister(followDto) == SUCCESS)
			return SUCCESS;
		else
			return FAIL;
	}

	@Override
	public List<FollowDto> followSelect(int no) {
		List<FollowDto> list = null;
		list = followDao.followSelect(no);
		return list;
	}

	@Override
	public List<FollowDto> followingSelect(int no) {
		List<FollowDto> list = null;
		list = followDao.followingSelect(no);
		return list;
	}

	@Override
	public int followDelete(FollowDto followDto) {
		if(followDao.followDelete(followDto) == SUCCESS)
			return SUCCESS;
		else
			return FAIL;
	}

	@Override
	public int followUpdate(FollowDto followDto) {
		if(followDao.followUpdate(followDto) == SUCCESS)
			return SUCCESS;
		else
			return FAIL;
	}

	@Override
	public FollowResultDto select(int sender, int receiver) {
		FollowResultDto followResultDto  = new FollowResultDto();
		FollowDto followDto = followDao.select(sender, receiver);
		
		if(followDto != null) {
			if(followDto.isType())
				followResultDto.setFollowResult(1);
			else
				followResultDto.setWaition(1);
		}
		List<FollowDto> follower = followDao.followSelect(receiver);
		List<FollowDto> following =  followDao.followingSelect(receiver);
		followResultDto.setFollower(follower);
		followResultDto.setFollowing(following);
		return followResultDto;
	}

}
