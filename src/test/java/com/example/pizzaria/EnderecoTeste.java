package com.example.pizzaria;

import com.example.pizzaria.controller.EnderecoController;
import com.example.pizzaria.dto.ClienteDTO;
import com.example.pizzaria.dto.EnderecoDTO;
import com.example.pizzaria.entity.Cliente;
import com.example.pizzaria.entity.Endereco;
import com.example.pizzaria.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EnderecoTeste {
    @MockBean
    EnderecoRepository enderecoRepository;

    @Autowired
    EnderecoController enderecoController;

    @BeforeEach
    void injectDados(){
        Endereco endereco = new Endereco();
        endereco.setId(1l);
        endereco.setCep("85858-330");
        endereco.setNuEndereco(1445);
        endereco.setRua("Jose epinafio teles Costa");
        endereco.setBairro("Morumbi");
        endereco.setTelResidencia("45 99999-8855");
        endereco.setComplemento("casa");
        endereco.setCliente(new Cliente("eduardo", "45 99815-6655", "111.888.999-78"));
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setNuEndereco(endereco.getNuEndereco());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setTelResidencia(endereco.getTelResidencia());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setCliente(endereco.getCliente());


    }

}
