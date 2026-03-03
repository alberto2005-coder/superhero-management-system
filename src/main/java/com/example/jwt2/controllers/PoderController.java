package com.example.jwt2.controllers;

import com.example.jwt2.entities.Poder;
import com.example.jwt2.services.PoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/poderes")
public class PoderController {

    @Autowired
    private PoderService poderService;

    @GetMapping
    public List<Poder> getAllPoderes() {
        return poderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poder> getPoderById(@PathVariable @org.springframework.lang.NonNull Long id) {
        return poderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sin-asignar")
    public List<Poder> getPoderesSinHeroes() {
        return poderService.findPoderesSinHeroes();
    }

    @DeleteMapping("/nombre/{nombre}")
    public ResponseEntity<Void> deletePoderPorNombre(@PathVariable String nombre) {
        poderService.deleteByNombre(nombre);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Poder> createPoder(@RequestBody @org.springframework.lang.NonNull Poder poder) {
        Poder nuevoPoder = poderService.save(poder);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoPoder.getId())
                .toUri();
        return ResponseEntity.created(location).body(nuevoPoder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poder> updatePoder(@PathVariable @org.springframework.lang.NonNull Long id,
            @RequestBody @org.springframework.lang.NonNull Poder poderDetails) {
        return poderService.update(id, poderDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoder(@PathVariable @org.springframework.lang.NonNull Long id) {
        if (poderService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
