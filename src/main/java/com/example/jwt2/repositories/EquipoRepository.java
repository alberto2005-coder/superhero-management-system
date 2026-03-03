package com.example.jwt2.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jwt2.entities.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
@Query("SELECT e FROM Equipo e WHERE size(e.heroes) > :minMiembros")
    List<Equipo> findEquiposConMasDeNMiembros(@Param("minMiembros") int minMiembros);
}