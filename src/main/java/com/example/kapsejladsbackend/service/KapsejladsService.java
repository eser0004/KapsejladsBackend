package com.example.kapsejladsbackend.service;
import com.example.kapsejladsbackend.model.BaadType;
import com.example.kapsejladsbackend.model.Kapsejlads;
import com.example.kapsejladsbackend.model.Sejlbaad;
import com.example.kapsejladsbackend.repository.DeltagerRepository;
import com.example.kapsejladsbackend.repository.KapsejladsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class KapsejladsService {

    private final KapsejladsRepository kapsejladsRepository;
   @Autowired
   DeltagerRepository deltagerRepository;

    public KapsejladsService(KapsejladsRepository kapsejladsRepository) {
        this.kapsejladsRepository = kapsejladsRepository;
    }

    public List<Kapsejlads> generateKapsejladser() {
        LocalDate startDato = LocalDate.of(2023, 5, 1);
        LocalDate slutDato = LocalDate.of(2023, 10, 1);
        List<Kapsejlads> kapsejladser = new ArrayList<>();

        for (LocalDate dato = startDato; !dato.isAfter(slutDato); dato = dato.plusDays(1)) {
            if (dato.getDayOfWeek().getValue() == 3) { // Onsdag
                for (BaadType baadType : BaadType.values()) { // Brug den fælles BaadType
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

    // Ny save-metode
    public Kapsejlads save(Kapsejlads kapsejlads) {
        return kapsejladsRepository.save(kapsejlads);
    }
    @Transactional
    public void deleteAllKapsejladser() {
        // Slet først alle deltagere
        deltagerRepository.deleteAll();

        // Derefter slet alle kapsejladser
        kapsejladsRepository.deleteAll();
    }


    public void generateKapsejladserForSeason() {
        LocalDate startDato = LocalDate.of(2023, 5, 1); // Sæson start
        LocalDate slutDato = LocalDate.of(2023, 10, 1); // Sæson slut

        // Iterer over alle datoer fra startDato til slutDato
        for (LocalDate dato = startDato; !dato.isAfter(slutDato); dato = dato.plusDays(1)) {
            // Tjek om dagen er en onsdag
            if (dato.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                // Opret en kapsejlads for hver bådtype
                for (BaadType baadType  : BaadType.values()) {
                    Kapsejlads kapsejlads = new Kapsejlads(dato, baadType, 10); // Fx 10 startende både
                    kapsejladsRepository.save(kapsejlads); // Gem i databasen
                }
            }
        }
    }
}