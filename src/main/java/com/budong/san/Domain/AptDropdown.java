package com.budong.san.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class AptDropdown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApt;
    private String aptName;
}
