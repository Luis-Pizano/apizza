package com.cft.apizza.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private int id;
    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @Column(length = 200)
    private String detalles;
    @Column(nullable = false)
    private int total;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalleOrdenes;
}