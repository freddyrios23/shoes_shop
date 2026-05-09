package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.MarcaDTO;
import com.shoes_shop.model.Marca;
import com.shoes_shop.repository.MarcaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    private MarcaDTO convertirDTO (Marca marca){
        MarcaDTO marcaDTO = new MarcaDTO();
        marcaDTO.setId(marca.getId());
        marcaDTO.setNombre(marca.getNombre());
        return marcaDTO;
    }

    public List<MarcaDTO> obtenerTodas(){
        return marcaRepository.findAll().stream().map(this::convertirDTO).toList();
    }

    public MarcaDTO buscarPorId(Integer id){
        Marca marca=marcaRepository.findById(id).orElseThrow(()-> new RuntimeException("Marca no econtrada"));
        return convertirDTO(marca);
    }

    public Marca guardarMarca(Marca marca){
        return marcaRepository.save(marca);
    }

    public Marca actualizarMarca (Integer id,Marca marca){
        Marca mark = marcaRepository.findById(id).orElseThrow(()-> new RuntimeException("Marca no encontrada en los registros"));
        if (marca.getNombre()!=null) {
            mark.setNombre(marca.getNombre());
        }
        return marcaRepository.save(mark);
    }

    public String eliminarMarca(Integer id){
        try {
            Marca marca = marcaRepository.findById(id).orElseThrow(()-> new RuntimeException("¿Imposible eliminar! Marca con el id" + id + "no existe"));
            marcaRepository.delete(marca);
            return "La Marca '" + marca.getId() + "' ha sido eliminada exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
