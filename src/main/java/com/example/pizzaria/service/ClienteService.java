package com.example.pizzaria.service;

import com.example.pizzaria.dto.ClienteDTO;
import com.example.pizzaria.entity.Cliente;
import com.example.pizzaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> findAll()
    {
        List<Cliente> clientes = this.clienteRepository.findAll();
        List<ClienteDTO> clientesDTO = new ArrayList<>();

        for(Cliente i : clientes)
        {
            clientesDTO.add(convertToDTO(i));
        }

        return clientesDTO;

    }

    public ClienteDTO findById(Long id)
    {
        Cliente cliente = this.clienteRepository.findById(id).orElse(null);

        return cliente == null
                ? null
                : convertToDTO(cliente);
    }

    public void cadastrar(ClienteDTO clienteDTO)
    {
        Assert.notNull(clienteDTO.getNome(), "Nome não pode ser nulo");
        Assert.notNull(clienteDTO.getTelCelular(), "Telefone não pode ser nulo");
        Assert.notNull(clienteDTO.getCpf(), "CPF não pode ser nulo");
        Assert.isTrue(!(this.clienteRepository.alreadyExists(clienteDTO.getCpf())), "CPF já cadastrado");

        Cliente cliente = convertToEntity(clienteDTO);

        this.clienteRepository.save(cliente);
    }

    public void editar(ClienteDTO clienteDTO, Long id)
    {
        Assert.notNull(clienteDTO.getNome(), "Nome não pode ser nulo");
        Assert.notNull(clienteDTO.getTelCelular(), "Telefone não pode ser nulo");
        Assert.notNull(clienteDTO.getCpf(), "CPF não pode ser nulo");
        if(this.clienteRepository.alreadyExists(clienteDTO.getCpf()))
        {
            Assert.isTrue( this.clienteRepository.isTheSame(clienteDTO.getCpf()).equals(id), "CPF já cadastrado");
        }

        Cliente cliente = convertToEntity(clienteDTO);

        this.clienteRepository.save(cliente);
    }

    public boolean deletar(Long id)
    {
        return true;
    }


    private ClienteDTO convertToDTO(Cliente cliente)
    {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setTelCelular(cliente.getTelCelular());

        return clienteDTO;
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO)
    {
        Cliente cliente = new Cliente();

        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelCelular(clienteDTO.getTelCelular());

        return cliente;
    }







}
