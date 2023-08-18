package com.example.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_pizza_pedidos", schema = "public")
@Data
public class PizzaPedido extends AbstractEntity{
@Column(name = "ds_observacao")
    private String observaca;
@Column(name = "vl_preco_pago"
    private double valor;
@Column(name = "id_pedido")
    private Pedido pedido;
@Column(name = "id_tipo_pizza")
    private PizzaTipo pizzaTipo;
}
