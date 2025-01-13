package com.example.kapsejladsbackend.repository;

import com.example.kapsejladsbackend.model.Kapsejlads;
import com.example.kapsejladsbackend.model.Sejlbaad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KapsejladsRepository extends JpaRepository<Kapsejlads, Long> {
    List<Kapsejlads> findByBaadType(Sejlbaad.BaadType baadType);
}