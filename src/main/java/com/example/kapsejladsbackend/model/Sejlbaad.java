package com.example.kapsejladsbackend.model;

import jakarta.persistence.*;

@Entity
public class Sejlbaad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String navn;

    @Enumerated(EnumType.STRING)
    private BaadType baadType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public BaadType getBaadType() {
        return baadType;
    }

    public void setBaadType(BaadType baadType) {
        this.baadType = baadType;
    }

    public enum BaadType {
        MINDRE_END_25FOD,
        MELLEM_25_40FOD,
        LAANGERE_END_40FOD
    }


}
