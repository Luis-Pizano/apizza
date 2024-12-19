package com.cft.apizza.repository;

import com.cft.apizza.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM pizza WHERE vegetariana = 1")
    List<Pizza> buscarPizzasVegetarianas();

    @Query(nativeQuery = true, value = "SELECT * FROM pizza WHERE descripcion like %:ingrediente%")
    List<Pizza> buscarIngrediente(@Param("ingrediente") String ingrediente);

    @Query(nativeQuery = true, value = "SELECT * FROM pizza WHERE disponible = 1")
    List<Pizza> buscarDisponibles();

    @Query(nativeQuery = true, value = "SELECT * FROM pizza WHERE precio = :precio")
    List<Pizza> buscarPorPrecio(@Param("precio") int precio);

    List<Pizza> findAllByDisponibleTrueOrderByPrecio();
    List<Pizza> findAllByDisponibleTrueAndDescripcionContainingIgnoreCase(String ingrediente);
    List<Pizza> findAllByDisponibleTrueAndDescripcionNotContainingIgnoreCase(String ingrediente);
    List<Pizza> findAllByPrecioLessThanEqualOrderByPrecioAsc(int precio);
    int countByVeganaTrue();

}
