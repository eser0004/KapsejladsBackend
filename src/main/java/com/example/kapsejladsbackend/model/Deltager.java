package com.example.kapsejladsbackend.model;

import jakarta.persistence.*;

@Entity
public class Deltager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kapsejlads_id", nullable = false)
    private Kapsejlads kapsejlads;

    @ManyToOne
    @JoinColumn(name = "sejlbaad_id", nullable = false)
    private Sejlbaad sejlbaad;

    private int point;

    public Deltager() {
    }

    public Deltager(Kapsejlads kapsejlads, Sejlbaad sejlbaad, int point) {
        this.kapsejlads = kapsejlads;
        this.sejlbaad = sejlbaad;
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kapsejlads getKapsejlads() {
        return kapsejlads;
    }

    public void setKapsejlads(Kapsejlads kapsejlads) {
        this.kapsejlads = kapsejlads;
    }

    public Sejlbaad getSejlbaad() {
        return sejlbaad;
    }

    public void setSejlbaad(Sejlbaad sejlbaad) {
        this.sejlbaad = sejlbaad;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}