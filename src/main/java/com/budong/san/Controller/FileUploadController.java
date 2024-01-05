package com.budong.san.Controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.budong.san.Domain.S3Url;
import com.budong.san.Service.S3UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final AmazonS3Client amazonS3Client;
    private final S3UrlService s3UrlService;

    @Value("${cookie.key}")
    private String cookieKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostMapping("/{bno}")
    public ResponseEntity<String> uploadFile(@CookieValue(value = "myCookie", required = false) Cookie cookie,
                                             @RequestParam("file") MultipartFile file,
                                             @PathVariable("bno") Long bno) {
        if (cookie == null) return null;
        else if (cookie.getValue().toString().equals(cookieKey)) {
            try {
                String fileName = file.getOriginalFilename();
                String fileUrl = "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/" + fileName;

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(file.getContentType());
                metadata.setContentLength(file.getSize());

                amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);

                S3Url s3Url = new S3Url();
                s3Url.setUrl(fileUrl);
                s3Url.setBno(bno);

                s3UrlService.saveUrl(s3Url);

                return ResponseEntity.ok(fileUrl);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else return null;
    }
}