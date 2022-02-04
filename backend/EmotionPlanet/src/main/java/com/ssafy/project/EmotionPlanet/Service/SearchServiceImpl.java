package com.ssafy.project.EmotionPlanet.Service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.project.EmotionPlanet.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.EmotionPlanet.Dao.CommentDao;
import com.ssafy.project.EmotionPlanet.Dao.FeedDao;
import com.ssafy.project.EmotionPlanet.Dao.ImgDao;
import com.ssafy.project.EmotionPlanet.Dao.SearchDao;
import com.ssafy.project.EmotionPlanet.Dao.TagDao;
import com.ssafy.project.EmotionPlanet.Dto.CommentDto;
import com.ssafy.project.EmotionPlanet.Dto.FeedDto;
import com.ssafy.project.EmotionPlanet.Dto.ImgDto;
import com.ssafy.project.EmotionPlanet.Dto.PickContentDto;
import com.ssafy.project.EmotionPlanet.Dto.PickDto;
import com.ssafy.project.EmotionPlanet.Dto.TagDto;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchDao searchDao;

	@Autowired
	FeedDao feedDao;

	@Autowired
	CommentDao commentDao;

	@Autowired
	TagDao tagDao;

	@Autowired
	ImgDao imgDao;

	@Override
	public List<TagDto> tagSelect(String name) {
		List<TagDto> list = searchDao.tagSelect(name);
		if (list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public List<FeedDto> tagListSelect(String name) {
		List<Integer> taglist = searchDao.tagListSelect(name);
		List<FeedDto> list = new ArrayList<FeedDto>();
		if (taglist.size() != 0) {
			for (int no : taglist) {
				list.add(feedDao.read(no));
			}
			for (FeedDto feed : list) {
				List<CommentDto> comments = commentDao.list(feed.getNo());
				List<TagDto> tags = tagDao.list(feed.getNo());
				List<ImgDto> imgs = imgDao.list(feed.getNo());
				feed.setComments(comments);
				feed.setTags(tags);
				feed.setImgs(imgs);
			}
			return list;
		} else {
			return null;
		}
	}

	@Override
	public List<UserDto> userSelect(String name) {
		List<UserDto> list = searchDao.userSelect(name);
		if(list.size() != 0) {
			return list;
		}else {
		return null;
		}
	}

//	@Override
//	public List<PickDto> recommendSelect(String name) {

//		List<PickDto> list = searchDao.s(name);
//		List<PickDto> list = searchDao.recommendSelect(name);
//		List<PickDto> list = searchDao.recommendSelect(name);
//		if(list.size() != 0) {
//			for (PickDto pick : list) {
//				List<PickContentDto> pickcontents = searchDao.pickcontentSelect(pick.getNo());
//				//pick.setPickcontents(pickcontents);
//			}
//			return list;
//		}else {
//			return null;
//		}
//	}
}