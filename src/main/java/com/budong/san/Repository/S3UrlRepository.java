package com.budong.san.Repository;

import com.budong.san.Domain.S3Url;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public interface S3UrlRepository {
    S3Url save(S3Url s3Url);
}
