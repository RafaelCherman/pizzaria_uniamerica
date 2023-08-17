package com.example.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_funcionarios", schema = "public")
public class Funcionario extends AbstractEntity{


    @Getter @Setter
    @Column(name = "nm_funcionario")
    private String nome;

    @Getter @Setter
    @Column(name = "nu_cpf_funcionario")
    private String cpf;

    @Getter @Setter
    @Column(name = "ds_funcao")
    private String funcao;
}
