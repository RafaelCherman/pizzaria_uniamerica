package com.example.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_clientes", schema = "public")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String tel_celular ;

    @Getter @Setter
    private String cpf;
}
