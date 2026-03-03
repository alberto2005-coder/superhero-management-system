package com.example.jwt2.services;

import com.example.jwt2.entities.Equipo;
import com.example.jwt2.repositories.EquipoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    public List<Equipo> findEquiposConMasDeNMiembros(int minMiembros) {
        return equipoRepository.findEquiposConMasDeNMiembros(minMiembros);
    }

    public Optional<Equipo> findById(@org.springframework.lang.NonNull Long id) {
        return equipoRepository.findById(id);
    }

    @Transactional
    public Equipo save(@org.springframework.lang.NonNull Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Transactional
    public Optional<Equipo> update(@org.springframework.lang.NonNull Long id,
            @org.springframework.lang.NonNull Equipo equipoDetails) {
        return equipoRepository.findById(id)
                .map(equipoExistente -> {
                    equipoExistente.setNombre(equipoDetails.getNombre());
                    return equipoRepository.save(equipoExistente);
                });
    }

    @Transactional
    public boolean deleteById(@org.springframework.lang.NonNull Long id) {
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
