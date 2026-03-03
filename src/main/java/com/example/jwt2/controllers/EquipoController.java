package com.example.jwt2.controllers;

import com.example.jwt2.entities.Equipo;
import com.example.jwt2.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<Equipo> getAllEquipos() {
        return equipoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable @org.springframework.lang.NonNull Long id) {
        return equipoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Equipo> createEquipo(@RequestBody @org.springframework.lang.NonNull Equipo equipo) {
        Equipo nuevoEquipo = equipoService.save(equipo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoEquipo.getId())
                .toUri();
        return ResponseEntity.created(location).body(nuevoEquipo);
    }

    @GetMapping("/min-miembros/{count}")
    public List<Equipo> getEquiposConMasDeNMiembros(@PathVariable int count) {
        return equipoService.findEquiposConMasDeNMiembros(count);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable @org.springframework.lang.NonNull Long id,
            @RequestBody @org.springframework.lang.NonNull Equipo equipoDetails) {
        return equipoService.update(id, equipoDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable @org.springframework.lang.NonNull Long id) {
        if (equipoService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}