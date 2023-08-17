package com.example.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_sabores", schema = "public")
@Data
public class Sabor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="nm_sabor")
    private String nome;
    private String ingredientes;



}
