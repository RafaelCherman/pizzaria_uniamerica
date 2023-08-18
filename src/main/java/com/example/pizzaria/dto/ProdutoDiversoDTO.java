package com.example.pizzaria.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoDiversoDTO {


    private Long id;

    private String nome;

    private String tipo;

    private double preco;

    private int quantidade;

    private boolean ativo;
}
