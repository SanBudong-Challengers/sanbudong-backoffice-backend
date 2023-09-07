package com.budong.san.Repository;

import com.budong.san.Domain.Building;
import com.budong.san.Service.BuildingService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JpaBuildingRepositoryTest {

    @Autowired
    BuildingService buildingService;

    @Autowired
    BuildingRepository repository;

    @Test
    public void save(){
        Building building = new Building();
        building.setAptName("1");
        building.setAptDong("2");
        building.setAptHo("3");
        building.setAptType("4");
        building.setAptSize(5);
        building.setAptDirection("6");
        building.setAptTransactionType("7");
        building.setAptPrice(8);
        building.setAptOption("9");
        building.setAptNote("10");
        building.setOwnerName("11");
        building.setOwnerPhone("12");
        building.setOwnerMobileCarrier("13");

        Building b1 = new Building();
        b1.setAptName("1");
        b1.setAptDong("2");
        b1.setAptHo("3");
        b1.setAptType("4");
        b1.setAptSize(11);
        b1.setAptDirection("6");
        b1.setAptTransactionType("7");
        b1.setAptPrice(20);
        b1.setAptOption("9");
        b1.setAptNote("10");
        b1.setOwnerName("11");
        b1.setOwnerPhone("12");
        b1.setOwnerMobileCarrier("13");

        buildingService.join(building);
        buildingService.join(b1);


    }

    @Test
    public void delete(){
        buildingService.deleteOne(5L);
    }

    @Test
    public void search(){
        List<Building> buildings = buildingService.findBySelection("",12,20,"7",10,20);
        for(Building building1 : buildings){
            System.out.println(building1.getBno());
        }
    }

    @Test
    public void all(){
        List<Building> buildings = buildingService.findBuildings();
        for(Building building1 : buildings){
            System.out.println(building1.getBno());
        }
    }
}
