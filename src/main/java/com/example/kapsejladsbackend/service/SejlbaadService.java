package com.example.kapsejladsbackend.service;

import com.example.kapsejladsbackend.model.BaadType;
import com.example.kapsejladsbackend.model.Sejlbaad;
import com.example.kapsejladsbackend.repository.SejlbaadRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SejlbaadService {

    private final SejlbaadRepository sejlbaadRepository;

    public SejlbaadService(SejlbaadRepository sejlbaadRepository) {
        this.sejlbaadRepository = sejlbaadRepository;
    }
    public List<Sejlbaad> findAll() {
        return sejlbaadRepository.findAll();
    }

    public Sejlbaad save(Sejlbaad sejlbaad) {
        return sejlbaadRepository.save(sejlbaad);
    }

    public void deleteById(Long id) {
        sejlbaadRepository.deleteById(id);
    }
    public Sejlbaad update(Long id, Sejlbaad updatedSejlbaad) {
        return sejlbaadRepository.findById(id)
                .map(sejlbaad -> {
                    sejlbaad.setNavn(updatedSejlbaad.getNavn());
                    sejlbaad.setBaadType(updatedSejlbaad.getBaadType());
                    return sejlbaadRepository.save(sejlbaad);
                })
                .orElseThrow(() -> new RuntimeException("Sejlbåd ikke fundet"));
    }
    @Transactional
    public void generateSejlbaade(int antal) {
        for (int i = 1; i <= antal; i++) {
            String navn = "Sejlbåd " + i;
            BaadType baadType = BaadType.values()[i % 3]; // Vælg bådtype cyklisk
            Sejlbaad sejlbaad = new Sejlbaad(navn, baadType);
            sejlbaadRepository.save(sejlbaad);
        }
    }


}
