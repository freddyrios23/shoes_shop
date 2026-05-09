package com.shoes_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoes_shop.DTO.ClienteDTO;
import com.shoes_shop.model.Boleta;
import com.shoes_shop.model.Cliente;
import com.shoes_shop.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO convertirDTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setTelefono(cliente.getTelefono());
        
        if (cliente.getBoletas()!= null) {
            clienteDTO.setBoletasId(cliente.getBoletas().stream().map(Boleta::getId).toList());
        }
        return clienteDTO;
    }

    public List<ClienteDTO> obtenertodos(){
        return clienteRepository.findAll().stream().map(this::convertirDTO).toList();
    }

    public ClienteDTO buscarPorId(Integer id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
        return convertirDTO(cliente);
    }

    public Cliente guardarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Integer id,Cliente cliente){
        Cliente client = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente no encontrado en los resgitros"));
        if (cliente.getRut()!=null) {
            client.setRut(cliente.getRut());
        }
        if (cliente.getNombre()!=null) {
            client.setNombre(cliente.getNombre());
        }
        if (cliente.getTelefono()!=null) {
            client.setTelefono(cliente.getTelefono());
        }
        if (cliente.getBoletas()!=null) {
            client.setBoletas(cliente.getBoletas());
        }
        return clienteRepository.save(client);
    }

    public String eliminarCliente(Integer id){
        try {
            Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("¡Imposible eliminar! El cliente con el id" + id + "no existe"));
            clienteRepository.delete(cliente);
            return "El cliente '" + cliente.getId() + "' ha sido eliminado exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
