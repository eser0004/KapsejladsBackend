package com.example.kapsejladsbackend.repository;

import com.example.kapsejladsbackend.model.Deltager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeltagerRepository extends JpaRepository<Deltager, Long> {
@Query("SELECT d.sejlbaad.navn, COUNT(d.sejlbaad.id) AS antalDeltagelser " +
        "FROM Deltager d " +
        "GROUP BY d.sejlbaad.id, d.sejlbaad.navn " +
        "ORDER BY antalDeltagelser DESC")
List<Object[]> findMostActiveSejlbaade();
}
