package com.cft.apizza.controller;

import com.cft.apizza.entities.DetalleOrden;
import com.cft.apizza.service.DetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalle_ordenes")
public class DetalleOrdenController {
    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @GetMapping
    public ResponseEntity<List<DetalleOrden>> getAllDetalleOrdens() {
        return ResponseEntity.ok(detalleOrdenService.getAllDetalleOrdens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrden> getDetalleOrdenById(@PathVariable int id) {
        Optional<DetalleOrden> detalleOrden = detalleOrdenService.getDetalleOrdenById(id);
        return detalleOrden.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleOrden> createDetalleOrden(@RequestBody DetalleOrden detalleOrden) {

        if (!detalleOrdenService.existsDetalleOrden(detalleOrden.getId())) {
            DetalleOrden newDetalleOrden = detalleOrdenService.saveDetalleOrden(detalleOrden);
            return new ResponseEntity<>(newDetalleOrden, HttpStatus.CREATED);
        }
        DetalleOrden newDetalleOrden = detalleOrdenService.saveDetalleOrden(detalleOrden);
        return ResponseEntity.badRequest().build();
    }
}
