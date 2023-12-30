package com.budong.san.Repository;

import com.budong.san.Domain.S3Url;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class JpaS3UrlRepository implements S3UrlRepository{

    private final EntityManager em;

    @Autowired
    public JpaS3UrlRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public S3Url save(S3Url s3Url) {
        em.persist(s3Url);
        return s3Url;
    }
}
