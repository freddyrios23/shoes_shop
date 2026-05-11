package com.shoes_shop.DTO;

import lombok.Data;

@Data
public class MetodoEnvioDTO {
    private Integer id;
    private String tipo;
    private Integer costo;
    private Integer tiempoEntrega;
    
}
