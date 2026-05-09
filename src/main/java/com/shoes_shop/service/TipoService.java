package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.TipoDTO;
import com.shoes_shop.model.Tipo;
import com.shoes_shop.repository.TipoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    public List<TipoDTO> obtenertodos(){
        return tipoRepository.findAll().stream().map(this::convertirDto).toList();
    }

    public TipoDTO buscarPorId(Integer id){
        Tipo tipo = tipoRepository.findById(id).orElseThrow(()-> new RuntimeException("¡Tipo no encontrado!"));
        return convertirDto(tipo);
    }

    public Tipo guardaTipo(Tipo tipo){
        return tipoRepository.save(tipo);
    }

    public Tipo actualizarTipo(Integer id,Tipo tipo){
        Tipo tipe = tipoRepository.findById(id).orElseThrow(()-> new RuntimeException("¡Tipo no encontrado en los registros!"));
        if (tipo.getNombre()!= null) {
            tipe.setNombre(tipo.getNombre());
        }
        return tipoRepository.save(tipe);
    }

    public String eliminarTipo(Integer id){
        try {
            Tipo tipo = tipoRepository.findById(id).orElseThrow(()-> new RuntimeException("¡Imposible eliminar! El tipo con el id" + id + "no existe"));
            tipoRepository.delete(tipo);
            return "El tipo '" + tipo.getNombre() + "' ha si eliminado";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private TipoDTO convertirDto(Tipo tipo){
        TipoDTO tipoDTO = new TipoDTO();
        tipoDTO.setId(tipo.getId());
        tipoDTO.setNombre(tipo.getNombre());
        return tipoDTO;
    }
}
