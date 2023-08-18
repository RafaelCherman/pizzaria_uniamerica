package com.example.pizzaria.dto;


import com.example.pizzaria.DTO.FuncionarioDTO;
import com.example.pizzaria.DTO.ProdutoDiversoDTO;

import lombok.Data;



import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
public class PedidoDTO {


    private Long id;
    private ClienteDTO cliente;

    private FuncionarioDTO atendente;

    private EnderecoDTO endereco;

    private FuncionarioDTO entregador;

    private boolean solicitaEntrega;

    private String pedido;

    private BigDecimal valorTotal;

    private String formaPagamento;

    private boolean entrega;

    private Date dataPedido;

    private Set<ProdutoDiversoDTO> produtos;

}
