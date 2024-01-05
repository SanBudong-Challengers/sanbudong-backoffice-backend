package com.budong.san.Repository;

import com.budong.san.Domain.Building;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaBuildingRepository implements BuildingRepository {

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
        em.createQuery("update Building b set b.aptName = :aptName, b.aptDong = :aptDong, b.aptHo = :aptHo, b.aptType = :aptType, b.aptSize = :aptSize, b.aptDirection = :aptDirection, b.aptTransactionType = :aptTransactionType, b.aptPrice = :aptPrice, b.aptOption = :aptOption, b.aptNote = :aptNote, b.ownerName = :ownerName, b.ownerPhone = :ownerPhone, b.ownerMobileCarrier = :ownerMobileCarrier where b.bno = :bno")
                .setParameter("aptName",building.getAptName())
                .setParameter("aptDong",building.getAptDong())
                .setParameter("aptHo",building.getAptHo())
                .setParameter("aptType",building.getAptType())
                .setParameter("aptSize", building.getAptSize())
                .setParameter("aptDirection",building.getAptDirection())
                .setParameter("aptTransactionType", building.getAptTransactionType())
                .setParameter("aptPrice", building.getAptPrice())
                .setParameter("aptOption", building.getAptOption())
                .setParameter("aptNote", building.getAptNote())
                .setParameter("ownerName", building.getOwnerName())
                .setParameter("ownerPhone", building.getOwnerPhone())
                .setParameter("ownerMobileCarrier", building.getOwnerMobileCarrier())
                .setParameter("bno",building.getBno())
                .executeUpdate();
        return building;
    }

    @Override
    public Long count() {
        String query = "select count(b.bno) from Building b";
        Long val = em.createQuery(query, Long.class).getSingleResult();
        return val;
    }

    @Override
    public List<Building> findAll(Integer page) {
        return em.createQuery("select b from Building b", Building.class)
                .setFirstResult(page)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public Building findByBno(Long bno) {
        Building building = em.createQuery("select b from Building b where b.bno = :bno",Building.class).setParameter("bno",bno).getSingleResult();
        System.out.println(building.getBno());
        return building;
    }

    @Override
    public void deleteOne(Long bno) {
        // 얘는 반환하는 애가 없기 때문에 query문 끝에 .class를 안 넣어도 됨
        em.createQuery("delete from Building b where b.bno = :bno")
                .setParameter("bno", bno)
                .executeUpdate();
    }

    @Override
    public List<Building> findBySelection(String aptName, double aptSizeMin, double aptSizeMax, String aptTransactionType, int aptPriceMin, int aptPriceMax, int page) {
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
                    .setFirstResult(page)
                    .setMaxResults(10)
                    .getResultList();
        else if (name == 1)
            return em.createQuery(sql, Building.class)
                    .setParameter("aptSizeMin", aptSizeMin)
                    .setParameter("aptSizeMax", aptSizeMax)
                    .setParameter("aptPriceMin", aptPriceMin)
                    .setParameter("aptPriceMax", aptPriceMax)
                    .setParameter("aptName", aptName)
                    .setFirstResult(page)
                    .setMaxResults(10)
                    .getResultList();
        else if (tt == 1)
            return em.createQuery(sql, Building.class)
                    .setParameter("aptSizeMin", aptSizeMin)
                    .setParameter("aptSizeMax", aptSizeMax)
                    .setParameter("aptPriceMin", aptPriceMin)
                    .setParameter("aptPriceMax", aptPriceMax)
                    .setParameter("aptTransactionType", aptTransactionType)
                    .setFirstResult(page)
                    .setMaxResults(10)
                    .getResultList();
        else
            return em.createQuery(sql, Building.class)
                    .setParameter("aptSizeMin", aptSizeMin)
                    .setParameter("aptSizeMax", aptSizeMax)
                    .setParameter("aptPriceMin", aptPriceMin)
                    .setParameter("aptPriceMax", aptPriceMax)
                    .setFirstResult(page)
                    .setMaxResults(10)
                    .getResultList();

    }

/*

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
*/
}
