package com.cft.apizza.controller;

import com.cft.apizza.entities.Orden;
import com.cft.apizza.entities.Pizza;
import com.cft.apizza.service.DetalleOrdenService;
import com.cft.apizza.service.OrdenService;
import com.cft.apizza.service.PizzaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @GetMapping
    public ResponseEntity<List<Orden>> getAllOrdenes() {
        return ResponseEntity.ok(ordenService.getAllOrdens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> getOrdenById(@PathVariable int id) {
        Optional<Orden> orden = ordenService.getOrdenById(id);
        return orden.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/pedidos")
    public ResponseEntity<ObjectNode> pedidos() {
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("key", "value");
        objectNode.put("foo", "bar");
        objectNode.put("number", 42);
        return ResponseEntity.ok(objectNode);
    }
    @PostMapping
    public ResponseEntity<Orden> createOrden(@RequestBody Orden orden) {

        if (!ordenService.existsOrden(orden.getId())) {
            Orden newOrden = ordenService.saveOrden(orden);

            int total = 0;
            for (int i = 0; i < newOrden.getDetalleOrdenes().size(); i++) {
                newOrden.getDetalleOrdenes().get(i).setOrden(newOrden);
                Pizza pizza = pizzaService.getPizzaById(orden.getDetalleOrdenes().get(i).getPizza().getId()).
                        orElseThrow(() -> new RuntimeException("Pizza no encontrada"));
                total += orden.getDetalleOrdenes().get(i).getCantidad() * pizza.getPrecio();
                detalleOrdenService.saveDetalleOrden(newOrden.getDetalleOrdenes().get(i));
            }
            newOrden.setTotal(total);
            newOrden = ordenService.saveOrden(newOrden);


            return new ResponseEntity<>(newOrden, HttpStatus.CREATED);
        }
        Orden newOrden = ordenService.saveOrden(orden);
        return ResponseEntity.badRequest().build();
    }
}
