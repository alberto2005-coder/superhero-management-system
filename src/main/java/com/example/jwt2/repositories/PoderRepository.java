package com.example.jwt2.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.jwt2.entities.Poder;

@Repository
public interface PoderRepository extends JpaRepository<Poder, Long> {
    @Query("SELECT p FROM Poder p WHERE p.heroes IS EMPTY")
    List<Poder> findPoderesSinHeroes();

    @Modifying
    @Query("DELETE FROM Poder p WHERE p.nombre = :nombre")
    void deleteByNombre(@Param("nombre") String nombre);
}
