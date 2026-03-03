package com.example.jwt2.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

@Entity
@Table(name = "heroes")
public class Heroe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "universo")
    private String universo;

    @Column(name = "base")
    private String base;

    @Column(name = "fecha_alta")
    private LocalDate fechaDeAlta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id")
    @JsonIgnoreProperties("heroes")
    private Equipo equipo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "heroe_poder", joinColumns = @JoinColumn(name = "heroe_id"), inverseJoinColumns = @JoinColumn(name = "poder_id"))

    @JsonIgnoreProperties("heroes")
    private Set<Poder> poderes = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "identidad_id", referencedColumnName = "id")
    @JsonIgnoreProperties("heroe")

    private IdentidadSecreta identidadSecreta;

    // --- Constructores ---
    public Heroe() {
        this.fechaDeAlta = LocalDate.now(); // Asignar fecha actual por defecto
    }

    public Heroe(String nombre, String universo, String base) {
        this.nombre = nombre;
        this.universo = universo;
        this.base = base;
        this.fechaDeAlta = LocalDate.now(); // Asignar fecha actual
    }

    // --- Getters y Setters ---

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

    public String getUniverso() {
        return universo;
    }

    public void setUniverso(String universo) {
        this.universo = universo;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Set<Poder> getPoderes() {
        return poderes;
    }

    public void setPoderes(Set<Poder> poderes) {
        this.poderes = poderes;
    }

    public IdentidadSecreta getIdentidadSecreta() {
        return identidadSecreta;
    }

    public void setIdentidadSecreta(IdentidadSecreta identidadSecreta) {
        this.identidadSecreta = identidadSecreta;
    }

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public void addPoder(Poder poder) {
        this.poderes.add(poder);
        poder.getHeroes().add(this);
    }

    public void removePoder(Poder poder) {
        this.poderes.remove(poder);
        poder.getHeroes().remove(this);
    }
}