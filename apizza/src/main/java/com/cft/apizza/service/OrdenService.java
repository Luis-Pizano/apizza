package com.cft.apizza.service;

import com.cft.apizza.entities.Orden;
import com.cft.apizza.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> getAllOrdens() {
        return ordenRepository.findAll();
    }

    public Optional<Orden> getOrdenById(Integer id) {
        return ordenRepository.findById(id);
    }

    public Orden saveOrden(Orden orden) {
        return ordenRepository.save(orden);
    }

    public void deleteOrden(Integer id) {
        ordenRepository.deleteById(id);
    }

    public boolean existsOrden(Integer id) {
        return this.ordenRepository.existsById(id);
    }
}
