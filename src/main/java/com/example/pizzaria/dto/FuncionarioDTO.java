package com.example.pizzaria.DTO;

import lombok.Getter;
import lombok.Setter;

public class FuncionarioDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nm_funcionario;

    @Getter @Setter
    private String nu_cpf_funcionario;

    @Getter @Setter
    private String ds_funcao;
}
