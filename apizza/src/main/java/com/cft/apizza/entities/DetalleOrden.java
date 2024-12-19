package com.cft.apizza.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_orden")
    private int id;

    @ManyToOne
    @JoinColumn(name = "pizza")
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "orden")
    @JsonIgnore
    private Orden orden;

    @Column(nullable = false)
    private int cantidad;
}
