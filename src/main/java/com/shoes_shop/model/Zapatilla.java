package com.shoes_shop.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "zapatillas")
public class Zapatilla {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min=3,max=100,message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false,length = 100)
    private String nombre;

    @Column(nullable = false)
    @Min(value = 1)
    @Max(value = 50)
    private Integer talla;

    @Column(nullable = false)
    @Min(value = 1)
    @Max(value = 999999)
    private Integer precio;

    @ManyToMany
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToMany(mappedBy = "zapatillas")
    private List<Boleta> boletas;
}
