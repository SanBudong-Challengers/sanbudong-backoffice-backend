package com.budong.san.Repository;

import com.budong.san.Domain.Building;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaBuildingRepository implements BuildingRepository{

    private final EntityManager em;

    @Autowired
    public JpaBuildingRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Building save(Building building) {
        em.persist(building);
        return building;
    }

    @Override
    public Building edit(Building building) {
        return null;
    }

    @Override
    public List<Building> findAll() {
        return em.createQuery("select b from Building b", Building.class).getResultList();
    }

    @Override
    public List<Building> findByName(String aptName) {
        return em.createQuery("select b from Building b where b.aptName = :aptName", Building.class)
                .setParameter("aptName", aptName)
                .getResultList();
    }

    @Override
    public List<Building> findByAptSize(int aptSize) {
        return null;
    }

    @Override
    public List<Building> findByTransactionType(String aptTransactionType) {
        return em.createQuery("select b from Building b where b.aptTransactionType = :aptTransactionType", Building.class)
                .setParameter("aptTransactionType", aptTransactionType)
                .getResultList();
    }

    @Override
    public List<Building> findByAptPrice(int min, int max) {
        return em.createQuery("select b from Building b where b.aptPrice >= :min and b.aptPrice <= :max", Building.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }

    @Override
    public void deleteOne(Long bno) {
        // 얘는 반환하는 애가 없기 때문에 query문 끝에 .class를 안 넣어도 됨
        em.createQuery("delete from Building b where b.bno = :bno")
                .setParameter("bno",bno)
                .executeUpdate();
    }

    @Override
    public List<Building> findBySelection(String aptName, int aptSizeMin, int aptSizeMax, String aptTransactionType, int aptPriceMin, int aptPriceMax) {
        String sql = "select b from Building b where b.aptSize >= :aptSizeMin and b.aptSize <= :aptSizeMax and b.aptPrice >= :aptPriceMin and b.aptPrice <= :aptPriceMax";
        int check = 0;

        int name = 0;
        if (aptName.length() != 0) {
            name = 1;
            sql = sql + " and aptName = :aptName";
        }

        int tt = 0;
        if (aptTransactionType.length() != 0) {
            tt = 1;
            sql = sql + " and aptTransactionType = :aptTransactionType";
        }

        if (name == 1 && tt == 1)
            return em.createQuery(sql, Building.class)
                    .setParameter("aptSizeMin", aptSizeMin)
                    .setParameter("aptSizeMax", aptSizeMax)
                    .setParameter("aptPriceMin", aptPriceMin)
                    .setParameter("aptPriceMax", aptPriceMax)
                    .setParameter("aptName", aptName)
                    .setParameter("aptTransactionType", aptTransactionType)
                    .getResultList();
        else if (name == 1)
            return em.createQuery(sql, Building.class)
                    .setParameter("aptSizeMin", aptSizeMin)
                    .setParameter("aptSizeMax", aptSizeMax)
                    .setParameter("aptPriceMin", aptPriceMin)
                    .setParameter("aptPriceMax", aptPriceMax)
                    .setParameter("aptName", aptName)
                    .getResultList();
        else if (tt == 1)
            return em.createQuery(sql, Building.class)
                    .setParameter("aptSizeMin", aptSizeMin)
                    .setParameter("aptSizeMax", aptSizeMax)
                    .setParameter("aptPriceMin", aptPriceMin)
                    .setParameter("aptPriceMax", aptPriceMax)
                    .setParameter("aptTransactionType", aptTransactionType)
                    .getResultList();
        else
            return em.createQuery(sql, Building.class)
                    .setParameter("aptSizeMin", aptSizeMin)
                    .setParameter("aptSizeMax", aptSizeMax)
                    .setParameter("aptPriceMin", aptPriceMin)
                    .setParameter("aptPriceMax", aptPriceMax)
                    .getResultList();

    }


}
