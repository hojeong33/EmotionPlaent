package com.ssafy.project.EmotionPlanet.Controller;

import com.ssafy.project.EmotionPlanet.Dto.FeedDto;
import com.ssafy.project.EmotionPlanet.Service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
        RequestMethod.OPTIONS })
@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class S3Controller {

    private final S3Service s3Service;

    private static final int SUCCESS = 1;

    @PostMapping("/file")
    public ResponseEntity<List<Integer>> uploadFile( @RequestPart(value = "file", required = true) List<MultipartFile> multipartFile) {
        List<Integer> result = s3Service.uploadFile(multipartFile);
        
        if(result.size() != 0) return new ResponseEntity<List<Integer>>(result, HttpStatus.OK);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "업로드 실패.");
    }

    @PostMapping(value = "/users/img") //회원 프로필 변경
    public ResponseEntity<String> uploadFileReturnURL(@RequestPart(value = "userNo", required = false) String no,
    		@RequestPart(value = "file", required = true) MultipartFile multipartFile) {
    	int userno = Integer.parseInt(no);
    	String result  = s3Service.uploadFileReturnURL(userno,multipartFile);

        if(result != null) {
        	System.out.println("프로필 변경 성공");
        	System.out.println(result);
        	return new ResponseEntity<String>(result, HttpStatus.OK);
        }
        else {
        	System.out.println("프로필 변경 실패");
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "업로드 실패.");
        }
    }

    @DeleteMapping("/file")
    public ResponseEntity<Integer> deleteFile(@RequestParam String fileName) {
        s3Service.deleteFile(fileName);
        return new ResponseEntity<Integer>(SUCCESS, HttpStatus.OK);
    }
}
