package com.example.kapsejladsbackend.repository;

import com.example.kapsejladsbackend.model.Sejlbaad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SejlbaadRepository extends JpaRepository<Sejlbaad, Long> {
}
