package com.example.kapsejladsbackend.controller;

import com.example.kapsejladsbackend.model.Sejlbaad;
import com.example.kapsejladsbackend.service.SejlbaadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sejlbaade")
public class SejlbaadController {
    private final SejlbaadService sejlbaadService;

    public SejlbaadController(SejlbaadService sejlbaadService) {
        this.sejlbaadService = sejlbaadService;
    }

    @GetMapping
    public List<Sejlbaad> getAll() {
        return sejlbaadService.findAll();
    }

    @PostMapping
    public Sejlbaad create(@RequestBody Sejlbaad sejlbaad) {
        return sejlbaadService.save(sejlbaad);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sejlbaadService.deleteById(id);
    }
    @PutMapping("/{id}")
    public Sejlbaad update(@PathVariable Long id, @RequestBody Sejlbaad updatedSejlbaad) {
        return sejlbaadService.update(id, updatedSejlbaad);
    }

}
