package com.example.pizzaria;

import com.example.pizzaria.controller.ClienteController;
import com.example.pizzaria.dto.ClienteDTO;
import com.example.pizzaria.entity.Cliente;
import com.example.pizzaria.repository.ClienteRepository;
import com.example.pizzaria.service.ClienteService;
import lombok.var;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClienteTeste {
    @MockBean
    ClienteRepository clienteRepository;
    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteController clienteController;

    @BeforeClass
    void injectDado(){

        Cliente cliente = new Cliente();
        cliente.setId(1l);
        cliente.setNome("Eduardo");
        cliente.setCpf("109.999.789-98");
        cliente.setTelCelular("45 99985-5563");
        List<Cliente> clientes  = new ArrayList<>();
        clientes.add(cliente);


        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(clienteDTO.getCpf());
        clienteDTO.setTelCelular(cliente.getTelCelular());
        List<ClienteDTO> clientesList  = new ArrayList<>();
        clientesList.add(clienteDTO);

        Mockito.when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        Mockito.when(clienteRepository.findAll()).thenReturn(clientes);
        Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
        Mockito.when(clienteRepository.alreadyExists(cliente.getCpf())).thenReturn(cliente.isAtivo());
        Mockito.when(clienteRepository.isTheSame(cliente.getCpf())).thenReturn(cliente.getId());

    }

    @Test
    void TesteFindByID(){
        var cliente = clienteController.findById(1l);
        Assert.assertEquals(1, cliente.getBody().getId(),0);
    }

    @Test
    void TesteFindByAll(){
        var clientes = clienteController.findAll();
        Assert.assertEquals(1, clientes.getBody().size());
    }

    @Test
    void TesteUpdate(){


    }





}