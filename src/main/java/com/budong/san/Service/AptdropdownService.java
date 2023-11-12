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

    public List<AptDropdown> findAparts(){
        List<AptDropdown> aptDropdownList = aptDropdownRepository.findAll();
        return aptDropdownList;
    }
}
