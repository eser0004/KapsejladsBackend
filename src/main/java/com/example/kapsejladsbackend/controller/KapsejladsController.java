package com.example.kapsejladsbackend.controller;

import com.example.kapsejladsbackend.model.Kapsejlads;
import com.example.kapsejladsbackend.model.BaadType; // Importer BaadType
import com.example.kapsejladsbackend.repository.KapsejladsRepository;
import com.example.kapsejladsbackend.service.KapsejladsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/kapsejladser")
public class KapsejladsController {

    private final KapsejladsService kapsejladsService;
    private final KapsejladsRepository kapsejladsRepository;

    public KapsejladsController(KapsejladsService kapsejladsService, KapsejladsRepository kapsejladsRepository) {
        this.kapsejladsService = kapsejladsService;
        this.kapsejladsRepository = kapsejladsRepository;
    }

    @GetMapping
    public List<Kapsejlads> getAll() {
        return kapsejladsService.findAll();
    }

    @PostMapping
    public Kapsejlads create(@RequestBody Map<String, Object> payload) {
        String dato = (String) payload.get("dato");
        String baadType = (String) payload.get("baadType");
        int antalStartendeBaade = (int) payload.get("antalStartendeBaade");

        Kapsejlads kapsejlads = new Kapsejlads(LocalDate.parse(dato), BaadType.valueOf(baadType), antalStartendeBaade);
        return kapsejladsRepository.save(kapsejlads);
    }

    @PostMapping("/generate")
    public String generateKapsejladserForSeason() {
        kapsejladsService.generateKapsejladserForSeason();
        return "Kapsejladser for hele sæsonen er oprettet!";
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllKapsejladser() {
        kapsejladsService.deleteAllKapsejladser();
        return "Alle kapsejladser og deres deltagere er slettet!";
    }



}
