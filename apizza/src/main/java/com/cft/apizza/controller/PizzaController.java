package com.cft.apizza.controller;

import com.cft.apizza.entities.Pizza;
import com.cft.apizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        return ResponseEntity.ok(pizzaService.getAllPizzas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable int id) {
        Optional<Pizza> pizza = pizzaService.getPizzaById(id);
        return pizza.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/vegetariana")
    public ResponseEntity<List<Pizza>> buscarPizzasVegetarianas(){
        return ResponseEntity.ok(pizzaService.buscarPizzasVegetarianas());
    }
    @GetMapping("/buscar_ingrediente/{ingrediente}")
    public ResponseEntity<List<Pizza>> buscarIngrediente(@PathVariable String ingrediente){
        return ResponseEntity.ok(pizzaService.buscarIngrediente(ingrediente));
    }
    @GetMapping("/disponible")
    public ResponseEntity<List<Pizza>> disponible(){
        return ResponseEntity.ok(pizzaService.buscarDisponibles());
    }
    @GetMapping("/precio/{precio}")
    public ResponseEntity<List<Pizza>> precio(@PathVariable int precio){
        return ResponseEntity.ok(pizzaService.buscarPorPrecio(precio));
    }
    @GetMapping("/available")
    public ResponseEntity<List<Pizza>> getAvailable(){
        return ResponseEntity.ok(pizzaService.getAvailable());
    }
    @GetMapping("/contiene/{ingrediente}")
    public ResponseEntity<List<Pizza>> contiene(@PathVariable String ingrediente){
        return ResponseEntity.ok(pizzaService.contiene(ingrediente));
    }
    @GetMapping("/nocontiene/{ingrediente}")
    public ResponseEntity<List<Pizza>> nocontiene(@PathVariable String ingrediente){
        return ResponseEntity.ok(pizzaService.noContiene(ingrediente));
    }
    @GetMapping("/menor_a/{precio}")
    public ResponseEntity<List<Pizza>> menorA(@PathVariable int precio){
        return ResponseEntity.ok(pizzaService.pizzasMenorA(precio));
    }

    @PostMapping
    public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {

        if (!pizzaService.existsPizza(pizza.getId())) {
            Pizza newPizza = pizzaService.savePizza(pizza);
            return new ResponseEntity<>(newPizza, HttpStatus.CREATED);
        }
        Pizza newPizza = pizzaService.savePizza(pizza);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Pizza> updatePizza(@RequestBody Pizza pizza) {
        if (pizzaService.existsPizza(pizza.getId())) {
            Pizza newPizza = pizzaService.savePizza(pizza);
            return ResponseEntity.ok(pizzaService.savePizza(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable Integer id) {
        pizzaService.deletePizza(id);
        return ResponseEntity.noContent().build();
    }
}