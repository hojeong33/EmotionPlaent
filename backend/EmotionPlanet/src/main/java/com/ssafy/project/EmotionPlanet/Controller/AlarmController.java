package com.ssafy.project.EmotionPlanet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.project.EmotionPlanet.Dto.AlarmDto;
import com.ssafy.project.EmotionPlanet.Service.AlarmService;

@RestController
public class AlarmController {
	
	@Autowired
	AlarmService alarmService;
	
    @MessageMapping("/alarm/send/{no}") // 메시지 받을 mapping 주소
    @SendTo("/alarm/receive/{no}") // 메시지를 url 로 반환
    // SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
    // 정의한 SocketVO를 1) 인자값, 2) 반환값으로 사용합니다.
    public AlarmDto SocketHandler(@DestinationVariable int no,@RequestParam AlarmDto alarmDto) { //pathvriable 친구
        System.out.println(alarmDto);
        // type 1.팔로우 2.댓글 3.좋아요
        alarmService.senderSelect(alarmDto);
        return alarmDto;
    }
}