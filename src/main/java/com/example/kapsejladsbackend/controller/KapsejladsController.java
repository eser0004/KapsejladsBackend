package com.example.kapsejladsbackend.controller;

import com.example.kapsejladsbackend.model.Kapsejlads;
import com.example.kapsejladsbackend.service.KapsejladsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/kapsejladser")
public class KapsejladsController {

    private final KapsejladsService kapsejladsService;

    public KapsejladsController(KapsejladsService kapsejladsService) {
        this.kapsejladsService = kapsejladsService;
    }

    @GetMapping
    public List<Kapsejlads> getAll() {
        return kapsejladsService.findAll();
    }

    @PostMapping("/generate")
    public List<Kapsejlads> generateKapsejladser() {
        return kapsejladsService.generateKapsejladser();
    }
}