package com.cft.apizza.repository;

import com.cft.apizza.entities.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    // Todas las ordenes
    @Query(value = "SELECT * FROM orden", nativeQuery = true)
    List<Orden> findAllOrdenes();

    //Ordenes con cliente
    @Query(value = "SELECT * FROM orden WHERE cliente_id = :id", nativeQuery = true)
    List<Orden> findOrdenesByClienteId(@Param("id") Integer clienteId);

    //Ordenes de mayor a menor
    @Query(value = "SELECT * FROM orden ORDER BY total DESC", nativeQuery = true)
    List<Orden> findOrdenesOrderByTotalDesc();

    //Ordenes por fecha
    @Query(value = "SELECT * FROM orden WHERE fecha = :fecha", nativeQuery = true)
    List<Orden> findOrdenesByFecha(@Param("fecha") String fecha);

    // Ordenes con monto igual a variable
    @Query(value = "SELECT * FROM orden WHERE total = :monto", nativeQuery = true)
    List<Orden> findOrdenesByTotal(@Param("monto") Double total);



}
