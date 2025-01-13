package com.example.kapsejladsbackend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Kapsejlads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dato;

    @Enumerated(EnumType.STRING)
    private Sejlbaad.BaadType baadType;

    private int antalStartendeBaade;

    @Column(nullable = false)
    private int pointVinder; // Point til førstepladsen

    @Column(nullable = false)
    private int pointIkkeFuldfort; // Point til bådene, der ikke fuldførte

    public Kapsejlads() {}

    public Kapsejlads(LocalDate dato, Sejlbaad.BaadType baadType, int antalStartendeBaade) {
        this.dato = dato;
        this.baadType = baadType;
        this.antalStartendeBaade = antalStartendeBaade;
        this.pointVinder = 1;
        this.pointIkkeFuldfort = antalStartendeBaade + 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public Sejlbaad.BaadType getBaadType() {
        return baadType;
    }

    public void setBaadType(Sejlbaad.BaadType baadType) {
        this.baadType = baadType;
    }

    public int getAntalStartendeBaade() {
        return antalStartendeBaade;
    }

    public void setAntalStartendeBaade(int antalStartendeBaade) {
        this.antalStartendeBaade = antalStartendeBaade;
    }

    public int getPointVinder() {
        return pointVinder;
    }

    public void setPointVinder(int pointVinder) {
        this.pointVinder = pointVinder;
    }

    public int getPointIkkeFuldfort() {
        return pointIkkeFuldfort;
    }

    public void setPointIkkeFuldfort(int pointIkkeFuldfort) {
        this.pointIkkeFuldfort = pointIkkeFuldfort;
    }

    // Getters og Setters
}