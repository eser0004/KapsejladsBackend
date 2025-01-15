package com.example.kapsejladsbackend.controller;

import com.example.kapsejladsbackend.model.BaadType;
import com.example.kapsejladsbackend.model.Deltager;
import com.example.kapsejladsbackend.model.Kapsejlads;
import com.example.kapsejladsbackend.model.Sejlbaad;
import com.example.kapsejladsbackend.repository.DeltagerRepository;
import com.example.kapsejladsbackend.repository.KapsejladsRepository;
import com.example.kapsejladsbackend.repository.SejlbaadRepository;
import com.example.kapsejladsbackend.service.DeltagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/deltagere")
public class DeltagerController {

    @Autowired
    DeltagerService deltagerService;
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
    @GetMapping("/most-active")
    public List<Map<String, Object>> getMostActiveSejlbaade() {
        return deltagerService.getMostActiveSejlbaade();
    }
    @PostMapping("/generate")
    public String generateDeltagere(@RequestParam int antal) {
        List<Kapsejlads> kapsejladser = kapsejladsRepository.findAll();
        List<Sejlbaad> sejlbaade = sejlbaadRepository.findAll();

        // Hvis ingen kapsejladser findes, opret mindst én
        if (kapsejladser.isEmpty()) {
            Kapsejlads defaultKapsejlads = new Kapsejlads(LocalDate.now(), BaadType.MINDRE_END_25FOD, 10);
            kapsejladser.add(kapsejladsRepository.save(defaultKapsejlads));
        }

        // Hvis ingen sejlbåde findes, opret mindst én
        if (sejlbaade.isEmpty()) {
            Sejlbaad defaultSejlbaad = new Sejlbaad("Default Båd", BaadType.MINDRE_END_25FOD);
            sejlbaade.add(sejlbaadRepository.save(defaultSejlbaad));
        }

        deltagerService.generateDeltagere(antal, kapsejladser, sejlbaade);
        return antal + " deltagere er genereret!";
    }



}