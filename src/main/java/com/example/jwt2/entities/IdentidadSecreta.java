// RUTA: com/example/heroes/entities/IdentidadSecreta.java
package com.example.jwt2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "identidades_secretas")
public class IdentidadSecreta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_real", nullable = false)
    private String nombreReal;

    @Column(name = "direccion")
    private String direccion;

    @OneToOne(mappedBy = "identidadSecreta", fetch = FetchType.LAZY)
    @JsonIgnore 
    private Heroe heroe;

    public IdentidadSecreta() {
    }

    public IdentidadSecreta(String nombreReal, String direccion) {
        this.nombreReal = nombreReal;
        this.direccion = direccion;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Heroe getHeroe() {
        return heroe;
    }

    public void setHeroe(Heroe heroe) {
        this.heroe = heroe;
    }
}