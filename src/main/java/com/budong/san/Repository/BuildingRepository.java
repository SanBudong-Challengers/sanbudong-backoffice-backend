package com.budong.san.Repository;

import com.budong.san.Domain.Building;

import java.util.List;

public interface BuildingRepository{
    Building save(Building building);
    Building edit(Building building);
    Building findByBno(Long bno);
    List<Building> findAll(Integer page);
    void deleteOne(Long bno);
    List<Building> findBySelection(String aptName, double aptSizeMin,double aptSizeMax, String aptTransactionType, int aptPriceMin, int aptPriceMax, int page);
    Long count();
    /*List<Building> findByName(String aptName);
    List<Building> findByAptSize(int aptSize);
    List<Building> findByTransactionType(String aptTransactionType);
    List<Building> findByAptPrice(int min, int max);*/
}
