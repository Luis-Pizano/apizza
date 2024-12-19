package com.cft.apizza.service;

import com.cft.apizza.entities.DetalleOrden;
import com.cft.apizza.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenService{
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    public List<DetalleOrden> getAllDetalleOrdens() {
        return detalleOrdenRepository.findAll();
    }

    public Optional<DetalleOrden> getDetalleOrdenById(Integer id) {
        return detalleOrdenRepository.findById(id);
    }

    public DetalleOrden saveDetalleOrden(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }

    public void deleteDetalleOrden(Integer id) {
        detalleOrdenRepository.deleteById(id);
    }

    public boolean existsDetalleOrden(Integer id) {
        return this.detalleOrdenRepository.existsById(id);
    }
}
