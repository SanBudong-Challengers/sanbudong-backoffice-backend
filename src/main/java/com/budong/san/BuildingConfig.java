package com.budong.san;

import com.budong.san.Repository.BuildingRepository;
import com.budong.san.Repository.JpaBuildingRepository;
import com.budong.san.Service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class BuildingConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public BuildingConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public BuildingService buildingService(){
        return new BuildingService(buildingRepository());
    }

    @Bean
    public BuildingRepository buildingRepository(){
        return new JpaBuildingRepository(em);
    }
}
