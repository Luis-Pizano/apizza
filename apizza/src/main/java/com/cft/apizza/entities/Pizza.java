package com.cft.apizza.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza")
    private int id;
    @Column(length = 50, unique = true)
    private String nombre;
    @Column(length = 100)
    private String descripcion;
    @Column(columnDefinition = "integer default 0")
    private int precio;
    @Column(columnDefinition = "TINYINT")
    private boolean vegana;
    @Column(columnDefinition = "TINYINT")
    private boolean vegetariana;
    @Column(columnDefinition = "TINYINT")
    private boolean disponible;
}