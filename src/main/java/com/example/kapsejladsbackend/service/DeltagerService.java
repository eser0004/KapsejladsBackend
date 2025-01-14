package com.example.kapsejladsbackend.service;

import com.example.kapsejladsbackend.model.Deltager;
import com.example.kapsejladsbackend.model.Kapsejlads;
import com.example.kapsejladsbackend.model.Sejlbaad;
import com.example.kapsejladsbackend.repository.DeltagerRepository;
import com.example.kapsejladsbackend.repository.KapsejladsRepository;
import com.example.kapsejladsbackend.repository.SejlbaadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}