package com.budong.san.Service;

import com.budong.san.Domain.S3Url;
import com.budong.san.Repository.JpaS3UrlRepository;
import com.budong.san.Repository.S3UrlRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class S3UrlService {
    private final S3UrlRepository s3UrlRepository;

    public S3UrlService(S3UrlRepository s3UrlRepository) {
        this.s3UrlRepository = s3UrlRepository;
    }

    public String saveUrl(S3Url s3Url){
        s3UrlRepository.save(s3Url);
        return s3Url.getUrl();
    }
}
