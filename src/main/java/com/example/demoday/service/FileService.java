package com.example.demoday.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.upload-dir}") // application.yml에서 파일 저장 경로 설정
    private String uploadDir;

    public String saveFile(MultipartFile file) throws IOException {
        // 업로드 폴더를 절대 경로로 지정
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            if (!uploadPath.mkdirs()) {
                throw new IOException("Failed to create upload directory: " + uploadDir);
            }
        }

        // 고유한 파일 이름 생성
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename.replaceAll("[^a-zA-Z0-9.]", "_");

        // 파일 저장
        File destinationFile = new File(uploadPath, uniqueFilename);
        file.transferTo(destinationFile);

        // 저장된 파일 경로 반환
        return uploadPath.getAbsolutePath() + File.separator + uniqueFilename;
    }
}
