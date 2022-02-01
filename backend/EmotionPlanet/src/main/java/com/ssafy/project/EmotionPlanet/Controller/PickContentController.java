package com.ssafy.project.EmotionPlanet.Controller;

import com.ssafy.project.EmotionPlanet.Dto.PickContentDto;
import com.ssafy.project.EmotionPlanet.Service.PickContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(
        origins = "http://localhost:5500",
        allowCredentials = "true",
        allowedHeaders ="*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS }
)
@RestController
public class PickContentController {


    @Autowired
    PickContentService pickContentService;

    private static final int SUCCESS = 1;

    @GetMapping(value ="/pickContent/{no}") // 목록 선탣
    public ResponseEntity<PickContentDto> select(@PathVariable String no) {
        int contentNo = Integer.parseInt(no);
        PickContentDto pickContentDto = pickContentService.select(contentNo);
        if(pickContentDto != null) {
            return new ResponseEntity<PickContentDto>(pickContentDto, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 컨텐츠를 찾을 수 없습니다.");
        }
    }

    @DeleteMapping(value ="/pickContent/{no}") // 목록 삭제
    public ResponseEntity<Integer> delete(@PathVariable String no) {
        int contentNo = Integer.parseInt(no);
        int result = pickContentService.delete(contentNo);
        if(result == SUCCESS) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 컨텐츠를 찾을 수 없습니다.");
        }
    }
}
