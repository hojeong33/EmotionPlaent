package com.ssafy.project.EmotionPlanet.Controller;

import com.ssafy.project.EmotionPlanet.Dto.CommentDto;
import com.ssafy.project.EmotionPlanet.Service.CommentServiceImpl;
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
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    private static final int SUCCESS = 1;

    @PostMapping(value ="/comments") // 댓글 작성
    public ResponseEntity<Integer> write(@RequestBody CommentDto commentDto) {
        int result = commentService.write(commentDto);
        if(result == SUCCESS) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "글 양식이 올바르지 않습니다.");
        }
    }

    @GetMapping(value ="/comments/{no}") // 댓글 목록
    public ResponseEntity<List<CommentDto>> list(@PathVariable String no) {
        int feedNo = Integer.parseInt(no);
        List<CommentDto> comments = commentService.list(feedNo);

        return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);

//        if(comments != null) {
//            return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 없습니다.");
//        }
    }

    @PutMapping(value ="/comments") // 댓글 수정
    public ResponseEntity<Integer> update(@RequestBody CommentDto commentDto) {
        int result = commentService.update(commentDto);
        if(result == SUCCESS) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정에 실패 했습니다.");
        }
    }

    @DeleteMapping(value ="/comments/{no}") // 댓글 삭제
    public ResponseEntity<Integer> delete(@PathVariable String no) {
        int commentNo = Integer.parseInt(no);
        int result = commentService.delete(commentNo);
        if(result == SUCCESS) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "피드를 찾을 수 없습니다.");
        }
    }
}
