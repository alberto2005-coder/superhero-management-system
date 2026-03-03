package com.example.jwt2.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt2.entities.Heroe;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Long> {

    Optional<Heroe> findFirstByNombre(String nombre);
    @Query("SELECT h FROM Heroe h WHERE h.universo = :universo")
    List<Heroe> findByUniverso(@Param("universo") String universo);

    
    @Query(value = "SELECT * FROM heroes WHERE nombre LIKE CONCAT(?1, '%')", nativeQuery = true)
    List<Heroe> findByNombreStartingWith(String prefijo);

    
    @Modifying
    @Query("UPDATE Heroe h SET h.base = :nuevaBase WHERE h.universo = :universo")
    int updateBaseByUniverso(@Param("universo") String universo, @Param("nuevaBase") String nuevaBase);

}