package com.example.kapsejladsbackend.service;
import com.example.kapsejladsbackend.model.Kapsejlads;
import com.example.kapsejladsbackend.model.Sejlbaad;
import com.example.kapsejladsbackend.repository.KapsejladsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class KapsejladsService {

    private final KapsejladsRepository kapsejladsRepository;

    public KapsejladsService(KapsejladsRepository kapsejladsRepository) {
        this.kapsejladsRepository = kapsejladsRepository;
    }

    public List<Kapsejlads> generateKapsejladser() {
        LocalDate startDato = LocalDate.of(2023, 5, 1);
        LocalDate slutDato = LocalDate.of(2023, 10, 1);
        List<Kapsejlads> kapsejladser = new ArrayList<>();

        for (LocalDate dato = startDato; !dato.isAfter(slutDato); dato = dato.plusDays(1)) {
            if (dato.getDayOfWeek().getValue() == 3) { // Onsdag
                for (Sejlbaad.BaadType baadType : Sejlbaad.BaadType.values()) {
                    Kapsejlads kapsejlads = new Kapsejlads(dato, baadType, 10); // Antal startende fx 10
                    kapsejladsRepository.save(kapsejlads);
                    kapsejladser.add(kapsejlads);
                }
            }
        }

        return kapsejladser;
    }

    public List<Kapsejlads> findAll() {
        return kapsejladsRepository.findAll();
    }
}