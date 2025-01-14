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

    public Sejlbaad() {}

    public Sejlbaad(String navn, BaadType baadType) {
        this.navn = navn;
        this.baadType = baadType;
    }


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
}
