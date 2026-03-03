package com.example.jwt2.repositories;

import com.example.jwt2.entities.IdentidadSecreta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentidadSecretaRepository extends JpaRepository<IdentidadSecreta, Long> {
}