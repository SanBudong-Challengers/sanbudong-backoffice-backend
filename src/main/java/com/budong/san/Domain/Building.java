package com.budong.san.Domain;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Building {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String aptName;
    private String aptDong;
    private String aptHo;
    private String aptType;
    private double aptSize;
    private String aptDirection;
    private String aptTransactionType;
    private int aptPrice;
    private String aptOption;
    private String aptNote;
    private String ownerName;
    private String ownerPhone;
    private String ownerMobileCarrier;
}
