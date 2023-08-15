package com.example.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_funcionarios", schema = "public")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    private String nm_funcionario;

    @Getter @Setter
    private String nu_cpf_funcionario;

    @Getter @Setter
    private String ds_funcao;
}
