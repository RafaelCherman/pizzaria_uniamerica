package com.example.pizzaria.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SaborDTO {
    private Long id;
    private boolean ativo;

    private String nome;

    private String ingredientes;

    private double valor;




}
