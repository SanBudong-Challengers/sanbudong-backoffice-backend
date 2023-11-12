package com.budong.san.Repository;

import com.budong.san.Domain.AptDropdown;

import java.util.List;

public interface AptDropdownRepository {
    AptDropdown save(AptDropdown aptDropdown);
    AptDropdown edit(AptDropdown aptDropdown);
    List<AptDropdown> findAll();
    void deleteOne(Long idApt);
}
