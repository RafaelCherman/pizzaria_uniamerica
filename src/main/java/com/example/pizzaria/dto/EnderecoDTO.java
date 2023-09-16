package com.example.pizzaria.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String rua;

    @Getter @Setter
    private int nuEndereco;

    @Getter @Setter
    private String bairro;

    @Getter @Setter
    private String cep;

    @Getter @Setter
    private String complemento;

    @Getter @Setter
    private ClienteDTO clienteDTO;

}
