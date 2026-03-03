package com.example.jwt2.services;

import com.example.jwt2.entities.Poder;
import com.example.jwt2.repositories.PoderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoderService {

    @Autowired
    private PoderRepository poderRepository;

    public List<Poder> findAll() {
        return poderRepository.findAll();
    }

    public Optional<Poder> findById(@org.springframework.lang.NonNull Long id) {
        return poderRepository.findById(id);
    }

    public List<Poder> findPoderesSinHeroes() {
        return poderRepository.findPoderesSinHeroes();
    }

    @Transactional
    public void deleteByNombre(String nombre) {
        poderRepository.deleteByNombre(nombre);
    }

    @Transactional
    public Poder save(@org.springframework.lang.NonNull Poder poder) {
        return poderRepository.save(poder);
    }

    @Transactional
    public Optional<Poder> update(@org.springframework.lang.NonNull Long id,
            @org.springframework.lang.NonNull Poder poderDetails) {
        return poderRepository.findById(id)
                .map(poderExistente -> {
                    poderExistente.setNombre(poderDetails.getNombre());
                    poderExistente.setDescripcion(poderDetails.getDescripcion());
                    return poderRepository.save(poderExistente);
                });
    }

    @Transactional
    public boolean deleteById(@org.springframework.lang.NonNull Long id) {
        if (poderRepository.existsById(id)) {
            poderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}