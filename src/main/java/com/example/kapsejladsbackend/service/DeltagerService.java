package com.example.kapsejladsbackend.service;

import com.example.kapsejladsbackend.model.Deltager;
import com.example.kapsejladsbackend.model.Kapsejlads;
import com.example.kapsejladsbackend.model.Sejlbaad;
import com.example.kapsejladsbackend.repository.DeltagerRepository;
import com.example.kapsejladsbackend.repository.KapsejladsRepository;
import com.example.kapsejladsbackend.repository.SejlbaadRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeltagerService {

    private final DeltagerRepository deltagerRepository;
    private final KapsejladsRepository kapsejladsRepository;
    private final SejlbaadRepository sejlbaadRepository;

    public DeltagerService(DeltagerRepository deltagerRepository,
                           KapsejladsRepository kapsejladsRepository,
                           SejlbaadRepository sejlbaadRepository) {
        this.deltagerRepository = deltagerRepository;
        this.kapsejladsRepository = kapsejladsRepository;
        this.sejlbaadRepository = sejlbaadRepository;
    }

    // Find alle deltagere
    public List<Deltager> findAll() {
        return deltagerRepository.findAll();
    }

    // Find deltager efter ID
    public Deltager findById(Long id) {
        return deltagerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Deltager med ID " + id + " blev ikke fundet."));
    }

    // Opret en ny deltager
    public Deltager create(Long kapsejladsId, Long sejlbaadId, int point) {
        Kapsejlads kapsejlads = kapsejladsRepository.findById(kapsejladsId)
                .orElseThrow(() -> new IllegalArgumentException("Kapsejlads med ID " + kapsejladsId + " blev ikke fundet."));
        Sejlbaad sejlbaad = sejlbaadRepository.findById(sejlbaadId)
                .orElseThrow(() -> new IllegalArgumentException("Sejlbaad med ID " + sejlbaadId + " blev ikke fundet."));

        Deltager deltager = new Deltager(kapsejlads, sejlbaad, point);
        return deltagerRepository.save(deltager);
    }

    // Opdater en deltager
    public Deltager update(Long id, int point) {
        Deltager deltager = deltagerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Deltager med ID " + id + " blev ikke fundet."));
        deltager.setPoint(point);
        return deltagerRepository.save(deltager);
    }

    // Slet en deltager
    public void delete(Long id) {
        if (!deltagerRepository.existsById(id)) {
            throw new IllegalArgumentException("Deltager med ID " + id + " blev ikke fundet.");
        }
        deltagerRepository.deleteById(id);
    }
    public List<Map<String, Object>> getMostActiveSejlbaade() {
        List<Object[]> results = deltagerRepository.findMostActiveSejlbaade();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> item = new HashMap<>();
            item.put("navn", result[0]);
            item.put("antalDeltagelser", result[1]);
            response.add(item);
        }

        return response;
    }
    @Transactional
    public void generateDeltagere(int antal, List<Kapsejlads> kapsejladser, List<Sejlbaad> sejlbaade) {
        Random random = new Random();
        for (int i = 1; i <= antal; i++) {
            Kapsejlads kapsejlads = kapsejladser.get(random.nextInt(kapsejladser.size()));
            Sejlbaad sejlbaad = sejlbaade.get(random.nextInt(sejlbaade.size()));
            int point = random.nextInt(10) + 1; // Generer tilfældige point mellem 1 og 10
            Deltager deltager = new Deltager(kapsejlads, sejlbaad, point);
            deltagerRepository.save(deltager);
        }
    }

}