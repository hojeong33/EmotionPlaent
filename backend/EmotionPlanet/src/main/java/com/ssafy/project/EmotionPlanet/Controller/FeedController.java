package com.ssafy.project.EmotionPlanet.Controller;

import com.ssafy.project.EmotionPlanet.Dto.FeedDto;
import com.ssafy.project.EmotionPlanet.Dto.FeedLikeDto;
import com.ssafy.project.EmotionPlanet.Service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(
        origins = "http://localhost:5500",
        allowCredentials = "true",
        allowedHeaders ="*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS }
)
@RestController
public class FeedController {
    @Autowired
    FeedService feedService;

    private static final int SUCCESS = 1;

    @PostMapping(value ="/feeds") // 글 작성
    public ResponseEntity<Integer> write(@RequestBody FeedDto feedDto) {
        int result = feedService.write(feedDto);
        if(result == SUCCESS) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "글 양식이 올바르지 않습니다.");
        }
    }

    @GetMapping(value ="/feeds/{no}") // 최신 피드 목록
    public ResponseEntity<List<FeedDto>> list(@PathVariable String no) {
        int userNo = Integer.parseInt(no);
        List<FeedDto> feeds = feedService.list(userNo);
        if(feeds != null) {
            return new ResponseEntity<List<FeedDto>>(feeds, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "최신피드가 없습니다.");
        }
    }

    @GetMapping(value ="/feeds/my/{no}") // 내가 작성한 피드 목록
    public ResponseEntity<List<FeedDto>> myList(@PathVariable String no) {
        int userNo = Integer.parseInt(no);
        List<FeedDto> feeds = feedService.myList(userNo);
        if(feeds != null) {
            return new ResponseEntity<List<FeedDto>>(feeds, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "내가 작성한 피드가 없습니다.");
        }
    }

    @GetMapping(value ="/feed/{no}") // 피드 정보
    public ResponseEntity<FeedDto> read(@PathVariable String no) {
        int feedNo = Integer.parseInt(no);
        FeedDto feed = feedService.read(feedNo);
        if(feed != null) {
            return new ResponseEntity<FeedDto>(feed, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "피드가 없습니다.");
        }
    }

    @PutMapping(value ="/feeds") // 글 작성
    public ResponseEntity<Integer> update(@RequestBody FeedDto feedDto) {
        int result = feedService.update(feedDto);
        if(result == SUCCESS) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정에 실패 했습니다.");
        }
    }

    @DeleteMapping(value ="/feeds/{no}") // 피드 삭제
    public ResponseEntity<Integer> delete(@PathVariable String no) {
        int feedNo = Integer.parseInt(no);
        int result = feedService.delete(feedNo);
        if(result == SUCCESS) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "피드를 찾을 수 없습니다.");
        }
    }

    @PostMapping(value ="/feeds/like") // 좋아요
    public ResponseEntity<Integer> like(@RequestBody FeedLikeDto feedLikeDto) {
        int feedNo = feedLikeDto.getFeedNo();
        int userNo = feedLikeDto.getUserNo();

        int result = feedService.like(userNo, feedNo);
        if(result == SUCCESS) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "오류 발생.");
        }
    }

    @DeleteMapping(value ="/feeds/like") // 좋아요 취소
    public ResponseEntity<Integer> unlike(@RequestBody FeedLikeDto feedLikeDto) {
        int feedNo = feedLikeDto.getFeedNo();
        int userNo = feedLikeDto.getUserNo();

        int result = feedService.unlike(userNo, feedNo);
        if(result == SUCCESS) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "오류 발생.");
        }
    }

}