package com.example.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_pizza_pedidos", schema = "public")
@Data
public class PizzaPedido extends AbstractEntity {
    @Column(name = "ds_observacao")
    private String observaca;
    @Column(name = "vl_preco_pago")
    private double valor;
    @Column(name = "id_pedido")
    @ManyToOne
    private Pedido pedido;

    @Column(name = "id_tipo_pizza")
    @OneToOne
    private PizzaTipo pizzaTipo;

    @ManyToMany
    @JoinColumn(name = "id_sabor")
    private List<Sabor> sabores;
}
