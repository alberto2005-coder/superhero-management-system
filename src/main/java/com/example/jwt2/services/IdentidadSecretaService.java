package com.example.jwt2.services;

import com.example.jwt2.entities.IdentidadSecreta;
import com.example.jwt2.repositories.IdentidadSecretaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentidadSecretaService {

    @Autowired
    private IdentidadSecretaRepository identidadRepository;

    public List<IdentidadSecreta> findAll() {
        return identidadRepository.findAll();
    }

    public Optional<IdentidadSecreta> findById(@org.springframework.lang.NonNull Long id) {
        return identidadRepository.findById(id);
    }

    @Transactional
    public IdentidadSecreta save(@org.springframework.lang.NonNull IdentidadSecreta identidad) {
        return identidadRepository.save(identidad);
    }

    @Transactional
    public Optional<IdentidadSecreta> update(@org.springframework.lang.NonNull Long id,
            @org.springframework.lang.NonNull IdentidadSecreta identidadDetails) {
        return identidadRepository.findById(id)
                .map(existente -> {
                    existente.setNombreReal(identidadDetails.getNombreReal());
                    existente.setDireccion(identidadDetails.getDireccion());
                    return identidadRepository.save(existente);
                });
    }

    @Transactional
    public boolean deleteById(@org.springframework.lang.NonNull Long id) {
        if (identidadRepository.existsById(id)) {
            identidadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}