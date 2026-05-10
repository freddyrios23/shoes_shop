package com.shoes_shop.service;

import com.shoes_shop.repository.BoletaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.BoletaDTO;
import com.shoes_shop.model.Boleta;
import com.shoes_shop.model.Boletas;
import com.shoes_shop.model.Zapatilla;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    private BoletaDTO convertirDTO (Boleta boleta){
        BoletaDTO boletaDto = new BoletaDTO(); 
        boletaDto.setId(boleta.getId());
        boletaDto.setFecha(boleta.getFecha());
        boletaDto.setTotal(boleta.getTotal());
        boletaDto.setCantidad(boleta.getCantidad());
        
        if (boleta.getBoletas() != null) {
            boletaDto.setZapatillasId(boleta.getBoletas().stream().map(Boletas::getId).toList());
        }
        return boletaDto;
    }

    public List<BoletaDTO> obtenerTodas(){
        return boletaRepository.findAll().stream().map(this::convertirDTO).toList();
    }

    public BoletaDTO buscarPorId(Integer id){
        Boleta boleta = boletaRepository.findById(id).orElseThrow(()-> new RuntimeException("Boleta no encontrada"));
        return convertirDTO(boleta);
    }

    public Boleta guardarBoleta(Boleta boleta){
        return boletaRepository.save(boleta);
    }

    public Boleta actualizarBoleta(Integer id,Boleta boleta){
        Boleta ticket = boletaRepository.findById(id).orElseThrow(()-> new RuntimeException("Boleta no encontrada en los resgitros"));
        if (boleta.getFecha()!=null) {
            ticket.setFecha(boleta.getFecha());
        }
        if (boleta.getTotal()!=null) {
            ticket.setTotal(boleta.getTotal());
        }
        if (boleta.getCantidad()!=null) {
            ticket.setCantidad(boleta.getCantidad());
        }
        if (boleta.getBoletas() !=null) {
            ticket.setBoletas(boleta.getBoletas());
        }
        return boletaRepository.save(ticket);
    }

    public String eliminarBoleta(Integer id){
        try {
            Boleta boleta = boletaRepository.findById(id).orElseThrow(()-> new RuntimeException("¡Imposible eliminar! La boleta con el id" + id + "no existe"));
            boletaRepository.delete(boleta);
            return "La boleta '" + boleta.getId() + "' ha sido eliminada exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
