package com.example.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="tb_produtos_diversos", schema = "public") // nome da tabela no banco de dados
@Data
public class ProdutoDiverso extends AbstractEntity{

    @Column(name="nm_produto")
private String nome;
    @Column(name="tp_produtos")
private String tipo;
    @Column(name="nu_preco_produto")
private double preco;
    @Column(name="qt_produtos")
private int quantidade;
}
