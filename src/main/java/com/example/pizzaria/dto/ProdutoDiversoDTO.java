package com.example.pizzaria.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProdutoDiversoDTO {


    private Long id;

    private String nome;

    private String tipo;

    private double preco;

    private int quantidade;
}
