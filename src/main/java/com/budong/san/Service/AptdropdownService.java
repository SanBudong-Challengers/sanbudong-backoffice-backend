package com.budong.san.Service;

import com.budong.san.Domain.AptDropdown;
import com.budong.san.Repository.AptDropdownRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class AptdropdownService {
    private final AptDropdownRepository aptDropdownRepository;

    public AptdropdownService(AptDropdownRepository aptDropdownRepository) {
        this.aptDropdownRepository = aptDropdownRepository;
    }

    public Long join(AptDropdown aptDropdown){
        aptDropdownRepository.save(aptDropdown);
        return aptDropdown.getIdApt();
    }

    public List<AptDropdown> findAparts(Integer page){
        List<AptDropdown> aptDropdownList = aptDropdownRepository.findAll(page);
        return aptDropdownList;
    }

    public void deleteApart(Long idApt){
        aptDropdownRepository.deleteOne(idApt);
    }

    public AptDropdown edit(AptDropdown aptDropdown){
        return aptDropdownRepository.edit(aptDropdown);
    }

    public AptDropdown findByIdApt(Long idApt){
        AptDropdown aptDropdown = aptDropdownRepository.findByIdApt(idApt);
        return aptDropdown;
    }
}
