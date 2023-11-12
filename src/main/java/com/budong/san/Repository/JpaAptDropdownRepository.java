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
        return null;
    }

    @Override
    public List<AptDropdown> findAll() {
        return em.createQuery("select a from AptDropdown a", AptDropdown.class).getResultList();
    }

    @Override
    public void deleteOne(Long idApt) {

    }
}
