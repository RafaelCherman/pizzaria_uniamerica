package com.example.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

public class ClienteDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String tel_celular ;

    @Getter @Setter
    private String cpf;
}
