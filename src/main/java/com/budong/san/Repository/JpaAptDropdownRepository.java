package com.budong.san.Repository;

import com.budong.san.Domain.AptDropdown;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaAptDropdownRepository implements AptDropdownRepository{
    private final EntityManager em;

    @Autowired
    public JpaAptDropdownRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public AptDropdown save(AptDropdown aptDropdown) {
        em.persist(aptDropdown);
        return aptDropdown;
    }

    @Override
    public AptDropdown edit(AptDropdown aptDropdown) {
        em.createQuery("update AptDropdown a set a.aptName = :aptName where a.idApt = :idApt")
                .setParameter("aptName", aptDropdown.getAptName())
                .setParameter("idApt",aptDropdown.getIdApt())
                .executeUpdate();
        return aptDropdown;
    }

    @Override
    public List<AptDropdown> findAll(Integer page) {
        return em.createQuery("select a from AptDropdown a", AptDropdown.class)
                .setFirstResult(page)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public AptDropdown findByIdApt(Long idApt) {
        return em.createQuery("select a from AptDropdown a where a.idApt = :idApt", AptDropdown.class)
                .setParameter("idApt", idApt)
                .getSingleResult();
    }

    @Override
    public void deleteOne(Long idApt) {
        em.createQuery("delete from AptDropdown a where a.idApt = :idApt")
                .setParameter("idApt", idApt)
                .executeUpdate();
    }
}
