package com.cft.apizza.controller;

import com.cft.apizza.entities.Cliente;
import com.cft.apizza.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable int id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {

        if (!clienteService.existsCliente(cliente.getId())) {
            Cliente newCliente = clienteService.saveCliente(cliente);
            return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
        }
        Cliente newCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.badRequest().build();
    }
}
