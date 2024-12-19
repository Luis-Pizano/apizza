package com.cft.apizza.repository;

import com.cft.apizza.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Mostrar los clientes cuyo nombre contenga una cadena de texto similar a la proporcionada
    @Query(value = "SELECT * FROM cliente WHERE LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))", nativeQuery = true)
    List<Cliente> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);

    // Mostrar los clientes que no hayan realizado ninguna orden
    @Query(value = "SELECT * FROM cliente c WHERE NOT EXISTS (SELECT 1 FROM orden o WHERE o.cliente_id = c.id_cliente)", nativeQuery = true)
    List<Cliente> findClientesSinOrdenes();
}
