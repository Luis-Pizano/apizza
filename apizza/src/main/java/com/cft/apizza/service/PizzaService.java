package com.cft.apizza.service;

import com.cft.apizza.entities.Pizza;
import com.cft.apizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> getPizzaById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void deletePizza(Integer id) {
        pizzaRepository.deleteById(id);
    }

    public boolean existsPizza(Integer id) {
        return this.pizzaRepository.existsById(id);
    }

    public List<Pizza> buscarPizzasVegetarianas() {
        return pizzaRepository.buscarPizzasVegetarianas();
    }
    public List<Pizza> buscarIngrediente(String ingrediente) {
        return pizzaRepository.buscarIngrediente(ingrediente);
    }
    public List<Pizza> buscarDisponibles() {
        return pizzaRepository.buscarDisponibles();
    }
    public List<Pizza> buscarPorPrecio(int precio) {
        return pizzaRepository.buscarPorPrecio(precio);
    }
    public List<Pizza> getAvailable() {
        return pizzaRepository.findAllByDisponibleTrueOrderByPrecio();
    }
    public List<Pizza> contiene(String ingrediente){
        return pizzaRepository.findAllByDisponibleTrueAndDescripcionContainingIgnoreCase(ingrediente);
    }
    public List<Pizza> noContiene(String ingrediente) {
        return pizzaRepository.findAllByDisponibleTrueAndDescripcionNotContainingIgnoreCase(ingrediente);
    }
    public List<Pizza> pizzasMenorA(int precio) {
        return pizzaRepository.findAllByPrecioLessThanEqualOrderByPrecioAsc(precio);
    }
}
