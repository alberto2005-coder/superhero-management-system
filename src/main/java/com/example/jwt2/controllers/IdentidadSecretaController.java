package com.example.jwt2.controllers;

import com.example.jwt2.entities.IdentidadSecreta;
import com.example.jwt2.services.IdentidadSecretaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/identidades")
public class IdentidadSecretaController {

    @Autowired
    private IdentidadSecretaService identidadService;

    @GetMapping
    public List<IdentidadSecreta> getAll() {
        return identidadService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdentidadSecreta> getById(@PathVariable @org.springframework.lang.NonNull Long id) {
        return identidadService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IdentidadSecreta> create(
            @RequestBody @org.springframework.lang.NonNull IdentidadSecreta identidad) {
        IdentidadSecreta nueva = identidadService.save(identidad);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nueva.getId())
                .toUri();
        return ResponseEntity.created(location).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdentidadSecreta> update(@PathVariable @org.springframework.lang.NonNull Long id,
            @RequestBody @org.springframework.lang.NonNull IdentidadSecreta identidadDetails) {
        return identidadService.update(id, identidadDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @org.springframework.lang.NonNull Long id) {
        if (identidadService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}