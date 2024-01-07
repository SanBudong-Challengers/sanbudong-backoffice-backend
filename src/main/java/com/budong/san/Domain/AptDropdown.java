package com.budong.san.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AptDropdown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApt;
    private String aptName;
}
