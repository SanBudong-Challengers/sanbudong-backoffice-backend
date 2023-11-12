package com.budong.san;

import com.budong.san.Repository.AptDropdownRepository;
import com.budong.san.Repository.JpaAptDropdownRepository;
import com.budong.san.Service.AptdropdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class AptDropdownConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public AptDropdownConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public AptdropdownService aptdropdownService(){
        return new AptdropdownService(aptDropdownRepository());
    }

    @Bean
    public AptDropdownRepository aptDropdownRepository(){
        return new JpaAptDropdownRepository(em);
    }
}
