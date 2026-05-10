package com.shoes_shop.model;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Color")
public class Color {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El color es obligatorio")
    @Size(min=3,max=30,message= "El color debe tener entre 3 y 30 caracteres")
    @Column(nullable = false,length = 30)
    private String color ;
}


