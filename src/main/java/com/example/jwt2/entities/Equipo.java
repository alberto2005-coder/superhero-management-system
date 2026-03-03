package com.example.jwt2.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @OneToMany(
            mappedBy = "equipo", 
            cascade = CascadeType.ALL,
            orphanRemoval = true 
    )
    @JsonIgnoreProperties("equipo") 
    private List<Heroe> heroes = new ArrayList<>();

    public Equipo() {
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Heroe> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Heroe> heroes) {
        this.heroes = heroes;
    }

    public void addHeroe(Heroe heroe) {
        heroes.add(heroe);
        heroe.setEquipo(this);
    }

    public void removeHeroe(Heroe heroe) {
        heroes.remove(heroe);
        heroe.setEquipo(null);
    }
}