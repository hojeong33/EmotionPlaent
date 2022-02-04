package com.ssafy.project.EmotionPlanet.Service;

import com.ssafy.project.EmotionPlanet.Dao.*;
import com.ssafy.project.EmotionPlanet.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    @Autowired
    TagService tagService;

    @Autowired
    S3Service s3Service;

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

        int result = feedDao.write(feedDto);
        System.out.println("피드번호 : " + feedDto.getNo());
        if(feedDto.getNo() != 0){

            for (ImgDto img : feedDto.getImgs()) {
                imgDao.relation(img.getNo(), feedDto.getNo());
            }

            for (TagDto tag : feedDto.getTags()) {
                tag.setFeedNo(feedDto.getNo());
                tagService.create(tag);
            }

            return result;
        } else return result;
    }

    @Override
    public int update(FeedDto feedDto) {

        return feedDao.update(feedDto);
    }

    @Override
    public int delete(int no) {

        List<CommentDto> comments = commentDao.list(no);
        List<ImgDto> imgs = imgDao.list(no);
        for(CommentDto comment : comments){
            commentDao.delete(comment.getNo());
        }
        for(ImgDto img : imgs){
            s3Service.deleteFile(img.getImgName());
        }
        return feedDao.delete(no);
    }

    @Override
    public List<UserRequestDto> likeList(int feedNo) {
        FeedDto feedDto = feedDao.read(feedNo);
        int userNo = feedDto.getAuthor();
        List<UserRequestDto> follow = feedDao.likeListFollow(userNo, feedNo);
        List<UserRequestDto> unfollow = feedDao.likeListUnFollow(userNo, feedNo);

        List<UserRequestDto> sum = new ArrayList<>();
        for (UserRequestDto user : follow) {
            sum.add(user);
        }
        for (UserRequestDto user : unfollow) {
            sum.add(user);
        }
        return sum;
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
