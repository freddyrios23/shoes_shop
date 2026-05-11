package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.SexoDTO;
import com.shoes_shop.model.Sexo;
import com.shoes_shop.repository.SexoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SexoService {

    @Autowired 
    private SexoRepository sexoRepository;

    private SexoDTO convertirDTO(Sexo sexo){
        SexoDTO sexoDTO = new SexoDTO();
        sexoDTO.setId(sexo.getId());
        sexoDTO.setGenero(sexo.getGenero());
        return sexoDTO;
    }

    public List<SexoDTO> obtenerTodos(){
        return sexoRepository.findAll().stream().map(this::convertirDTO).toList();
    }

    public SexoDTO buscarPorId(Integer id){
        Sexo sexo = sexoRepository.findById(id).orElseThrow(() -> new RuntimeException("¡Sexo no encontrada!"));
        return convertirDTO(sexo);
    }

    public Sexo guardarSexo(Sexo sexo){
        return sexoRepository.save(sexo);
    }

    public Sexo actualizarSexo(Integer id, Sexo sexo){
        Sexo sex = sexoRepository.findById(id).orElseThrow(() -> new RuntimeException("¡Sexo no encontrada! no existe en los registros"));
        if (sexo.getGenero()!= null) {
            sex.setGenero(sexo.getGenero());
        }
        return sexoRepository.save(sex);
    }

    public String eliminarSexo(Integer id){
        try {
            Sexo sexo = sexoRepository.findById(id).orElseThrow(()-> new RuntimeException("¡Imposible eliminar! el sexo con el id" + id + "no existe"));
            sexoRepository.delete(sexo);
            return "el sexo '" + sexo.getGenero() + "' ha sido eliminado exitosamente.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
