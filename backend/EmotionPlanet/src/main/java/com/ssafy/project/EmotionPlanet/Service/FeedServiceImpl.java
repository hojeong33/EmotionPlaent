package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dao.*;
import com.ssafy.project.EmotionPlanet.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService{

    @Autowired
    FeedDao feedDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    TagDao tagDao;

    @Autowired
    ImgDao imgDao;

    @Override
    public List<FeedDto> list(int no) {

        List<FeedDto> feeds = feedDao.list(no);
        for(FeedDto feed : feeds){
            List<CommentDto> comments = commentDao.list(feed.getNo());
            List<TagDto> tags = tagDao.list(feed.getNo());
            List<ImgDto> imgs = imgDao.list(feed.getNo());
            feed.setComments(comments);
            feed.setTags(tags);
            feed.setImgs(imgs);
        }

        return feeds;
    }

    @Override
    public List<FeedDto> myList(int no) {

        List<FeedDto> feeds = feedDao.myList(no);
        for(FeedDto feed : feeds){
            List<CommentDto> comments = commentDao.list(feed.getNo());
            List<TagDto> tags = tagDao.list(feed.getNo());
            List<ImgDto> imgs = imgDao.list(feed.getNo());
            feed.setComments(comments);
            feed.setTags(tags);
            feed.setImgs(imgs);
        }

        return feeds;
    }

    @Override
    public FeedDto read(int no) {

        FeedDto feed = feedDao.read(no);
        List<CommentDto> comments = commentDao.list(feed.getNo());
        List<TagDto> tags = tagDao.list(feed.getNo());
        List<ImgDto> imgs = imgDao.list(feed.getNo());
        feed.setComments(comments);
        feed.setTags(tags);
        feed.setImgs(imgs);
        return feed;

    }

    @Override
    public int write(FeedDto feedDto) {
        return feedDao.write(feedDto);
    }

    @Override
    public int update(FeedDto feedDto) {
        

        return feedDao.update(feedDto);
    }

    @Override
    public int delete(int no) {

        List<CommentDto> comments = commentDao.list(no);
        for(CommentDto comment : comments){
            commentDao.delete(comment.getNo());
        }
        return feedDao.delete(no);
    }

    @Override
    public int like(int userNo, int feedNo) {
        return feedDao.like(userNo, feedNo);
    }

    @Override
    public int unlike(int userNo, int feedNo) {
        return feedDao.unlike(userNo, feedNo);
    }
}
