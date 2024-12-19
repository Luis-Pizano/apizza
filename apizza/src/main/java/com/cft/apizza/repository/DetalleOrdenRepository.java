package com.cft.apizza.repository;

import com.cft.apizza.entities.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {

    // Buscar todos los detalles de una orden específica
    @Query(value = "SELECT * FROM detalle_orden d WHERE d.orden_id = :ordenId", nativeQuery = true)
    List<DetalleOrden> findByOrdenId(@Param("ordenId") Integer ordenId);

    // Buscar todos los detalles que incluyan una pizza específica
    @Query(value = "SELECT * FROM detalle_orden d WHERE d.pizza_id = :pizzaId", nativeQuery = true)
    List<DetalleOrden> findByPizzaId(@Param("pizzaId") Integer pizzaId);

    // Buscar detalles de una orden ordenados por cantidad descendente
    @Query(value = "SELECT * FROM detalle_orden d WHERE d.orden_id = :ordenId ORDER BY d.cantidad DESC", nativeQuery = true)
    List<DetalleOrden> findByOrdenIdOrderByCantidadDesc(@Param("ordenId") Integer ordenId);

    // Buscar todos los detalles con una cantidad mayor o igual a un valor dado
    @Query(value = "SELECT * FROM detalle_orden d WHERE d.cantidad >= :cantidad", nativeQuery = true)
    List<DetalleOrden> findByCantidadGreaterThanEqual(@Param("cantidad") Integer cantidad);

    // Buscar todos los detalles con una cantidad menor o igual a un valor dado
    @Query(value = "SELECT * FROM detalle_orden d WHERE d.cantidad <= :cantidad", nativeQuery = true)
    List<DetalleOrden> findByCantidadLessThanEqual(@Param("cantidad") Integer cantidad);
}
