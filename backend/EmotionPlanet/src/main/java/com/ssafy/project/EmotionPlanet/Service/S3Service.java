package com.ssafy.project.EmotionPlanet.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Service {

    public List<Integer> uploadFile(List<MultipartFile> multipartFile);
    public List<String> uploadFileReturnURL(List<MultipartFile> multipartFile);
    public void deleteFile(String fileName);
}
