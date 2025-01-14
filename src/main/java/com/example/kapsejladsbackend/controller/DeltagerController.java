package com.example.kapsejladsbackend.controller;

import com.example.kapsejladsbackend.model.Deltager;
import com.example.kapsejladsbackend.model.Kapsejlads;
import com.example.kapsejladsbackend.model.Sejlbaad;
import com.example.kapsejladsbackend.repository.DeltagerRepository;
import com.example.kapsejladsbackend.repository.KapsejladsRepository;
import com.example.kapsejladsbackend.repository.SejlbaadRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/deltagere")
public class DeltagerController {

    private final DeltagerRepository deltagerRepository;
    private final KapsejladsRepository kapsejladsRepository;
    private final SejlbaadRepository sejlbaadRepository;

    public DeltagerController(DeltagerRepository deltagerRepository,
                              KapsejladsRepository kapsejladsRepository,
                              SejlbaadRepository sejlbaadRepository) {
        this.deltagerRepository = deltagerRepository;
        this.kapsejladsRepository = kapsejladsRepository;
        this.sejlbaadRepository = sejlbaadRepository;
    }

    @GetMapping
    public List<Deltager> getAll() {
        return deltagerRepository.findAll();
    }

    @PostMapping
    public Deltager create(@RequestBody Map<String, Object> payload) {
        Long kapsejladsId = ((Number) payload.get("kapsejladsId")).longValue();
        Long sejlbaadId = ((Number) payload.get("sejlbaadId")).longValue();
        int point = ((Number) payload.get("point")).intValue();

        Kapsejlads kapsejlads = kapsejladsRepository.findById(kapsejladsId)
                .orElseThrow(() -> new IllegalArgumentException("Kapsejlads ikke fundet"));
        Sejlbaad sejlbaad = sejlbaadRepository.findById(sejlbaadId)
                .orElseThrow(() -> new IllegalArgumentException("Sejlbaad ikke fundet"));

        Deltager deltager = new Deltager(kapsejlads, sejlbaad, point);
        return deltagerRepository.save(deltager);
    }



    @PutMapping("/{id}")
    public Deltager update(@PathVariable Long id, @RequestParam int point) {
        Deltager deltager = deltagerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Deltager ikke fundet"));

        deltager.setPoint(point);
        return deltagerRepository.save(deltager);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deltagerRepository.deleteById(id);
    }
}