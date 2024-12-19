package com.cft.apizza.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cliente") // Nombre explícito de la tabla
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente") // Nombre explícito de la columna
    private int id;

    @Column(name = "nombre", length = 50, nullable = false) // Nombre y restricciones explícitas
    private String nombre;

    @Column(name = "correo", length = 50, unique = true) // Columna única
    private String correo;

    @Column(name = "telefono", length = 20, nullable = false) // Columna obligatoria
    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Orden> ordenes;
}
