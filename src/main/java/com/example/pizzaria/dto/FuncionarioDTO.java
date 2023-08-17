package com.example.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

public class FuncionarioDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String cpf;

    @Getter @Setter
    private String funcao;
}
