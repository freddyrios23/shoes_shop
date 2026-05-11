package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.MetodoPagoDTO;
import com.shoes_shop.model.MetodoPago;
import com.shoes_shop.repository.MetodoPagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    private MetodoPagoDTO convertirADTO(MetodoPago metodoPago){
        MetodoPagoDTO metodoPagoDTO = new MetodoPagoDTO();
        metodoPagoDTO.setId(metodoPago.getId());
        metodoPago.setTipo(metodoPago.getTipo());
        return metodoPagoDTO;
    }

    public List<MetodoPagoDTO> obtenerTodos(){
        return metodoPagoRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    public MetodoPagoDTO buscarPorId(Integer id){
        MetodoPago metodoPago = metodoPagoRepository.findById(id).orElseThrow(()-> new RuntimeException("Metodo pago no econtrada"));
        return convertirADTO(metodoPago);
    }

    public MetodoPago guardarMetodoPago(MetodoPago metodoPago){
        return metodoPagoRepository.save(metodoPago);
    }

    public MetodoPago actualizarMetodoPago(Integer id,MetodoPago metodoPago){
        MetodoPago metodoPago2 = metodoPagoRepository.findById(id).orElseThrow(()-> new RuntimeException("Metodo pago no encontrada en los registros"));
        if (metodoPago.getTipo()!= null) {
            metodoPago2.setTipo(metodoPago.getTipo());
        }
        return metodoPagoRepository.save(metodoPago2);
    }

    public String eliminarMetodoPAgo(Integer id){
        try {
            MetodoPago metodoPago = metodoPagoRepository.findById(id).orElseThrow(()-> new RuntimeException("¿Imposible eliminar! Marca con el id" + id + "no existe"));
            metodoPagoRepository.delete(metodoPago);
            return "El metodo de pago '" + metodoPago.getId() + "' ha sido eliminado exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
