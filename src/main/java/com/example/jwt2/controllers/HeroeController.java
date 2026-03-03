package com.example.jwt2.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jwt2.dto.PoderAsignarDTO;
import com.example.jwt2.entities.Heroe;
import com.example.jwt2.services.HeroeService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/heroes")
public class HeroeController {

    @Autowired
    private HeroeService heroeService;

    @GetMapping
    public List<Heroe> getAllHeroes() {
        return heroeService.findAll();
    }

    @GetMapping("/buscar")
    public List<Heroe> buscarHeroes(
            @RequestParam(required = false) String termino,
            @RequestParam(required = false) String universo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate altaDespuesDe,
            @RequestParam(required = false, defaultValue = "nombre") String ordenarPor,
            @RequestParam(required = false, defaultValue = "asc") String ordenDir) {
        return heroeService.buscarHeroesConCriteria(termino, universo, altaDespuesDe, ordenarPor, ordenDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Heroe> getHeroeById(@PathVariable @org.springframework.lang.NonNull Long id) {
        return heroeService.findById(id)
                .map(heroe -> ResponseEntity.ok(heroe))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/universo/{universo}")
    public List<Heroe> getHeroesByUniverso(@PathVariable String universo) {
        return heroeService.findByUniverso(universo);
    }

    @GetMapping("/buscar/nombre")
    public List<Heroe> getHeroesByNombreStartingWith(@RequestParam("prefijo") String prefijo) {
        return heroeService.findByNombreStartingWith(prefijo);
    }

    @PutMapping("/universo/{universo}/base")
    public ResponseEntity<String> updateBaseByUniverso(
            @PathVariable String universo,
            @RequestBody String nuevaBase) {
        Heroe heroeDetails = new Heroe();
        heroeDetails.setBase(nuevaBase);

        int actualizados = heroeService.updateBaseByUniverso(universo, heroeDetails.getBase());
        return ResponseEntity.ok("Héroes actualizados: " + actualizados);
    }

    @PostMapping
    public ResponseEntity<Heroe> createHeroe(@RequestBody @org.springframework.lang.NonNull Heroe heroe) {
        Heroe nuevoHeroe = heroeService.save(heroe);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoHeroe.getId())
                .toUri();
        return ResponseEntity.created(location).body(nuevoHeroe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Heroe> updateHeroe(@PathVariable @org.springframework.lang.NonNull Long id,
            @RequestBody @org.springframework.lang.NonNull Heroe heroeDetails) {
        return heroeService.update(id, heroeDetails)
                .map(heroeActualizado -> ResponseEntity.ok(heroeActualizado))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHeroe(@PathVariable @org.springframework.lang.NonNull Long id) {
        if (heroeService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{heroeId}/poderes")
    public ResponseEntity<Heroe> addPoderToHeroe(
            @PathVariable @org.springframework.lang.NonNull Long heroeId,
            @RequestBody PoderAsignarDTO poderDTO) {

        return heroeService.addPoderToHeroe(heroeId, java.util.Objects.requireNonNull(poderDTO.getPoderId()))
                .map(heroeActualizado -> ResponseEntity.ok(heroeActualizado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{heroeId}/poderes/{poderId}")
    public ResponseEntity<Heroe> removePoderFromHeroe(
            @PathVariable @org.springframework.lang.NonNull Long heroeId,
            @PathVariable @org.springframework.lang.NonNull Long poderId) {

        return heroeService.removePoderFromHeroe(heroeId, poderId)
                .map(heroeActualizado -> ResponseEntity.ok(heroeActualizado))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}