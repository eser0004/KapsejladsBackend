package com.example.kapsejladsbackend.repository;

import com.example.kapsejladsbackend.model.Deltager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeltagerRepository extends JpaRepository<Deltager, Long> {}
