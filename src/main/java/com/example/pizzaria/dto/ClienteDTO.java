package com.example.pizzaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO extends AbstractEntityDTO{

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String telCelular ;

    @Getter @Setter
    private String cpf;
}
