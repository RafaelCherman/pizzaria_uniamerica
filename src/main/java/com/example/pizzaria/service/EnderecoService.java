package com.example.pizzaria.service;

import com.example.pizzaria.dto.ClienteDTO;
import com.example.pizzaria.dto.EnderecoDTO;
import com.example.pizzaria.entity.Cliente;
import com.example.pizzaria.entity.Endereco;
import com.example.pizzaria.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoDTO> findAll()
    {
        List<Endereco> enderecos = this.enderecoRepository.findAll();
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();

        for(Endereco i : enderecos)
        {
            enderecosDTO.add(convertToDTO(i));
        }

        return enderecosDTO;
    }

    public EnderecoDTO findById(Long id)
    {
        Endereco endereco = this.enderecoRepository.findById(id).orElse(null);

        return endereco == null
                ? null
                : convertToDTO(endereco);
    }

    public void cadastrar(EnderecoDTO enderecoDTO)
    {
        Assert.notNull(enderecoDTO.getRua(), "Rua não pode ser nula");
        Assert.notNull(enderecoDTO.getNuEndereco(), "Numero do endereço não pode ser nulo");
        Assert.notNull(enderecoDTO.getBairro(), "Bairro não pode ser nulo");
        Assert.notNull(enderecoDTO.getCliente(), "Cliente não pode ser nulo");
        Endereco endereco = convertToEntity(enderecoDTO);

        this.enderecoRepository.save(endereco);
    }

    public void editar(EnderecoDTO enderecoDTO, Long id)
    {
        Assert.isTrue(enderecoRepository.doesExist(id), "Endereço não existe");
        Assert.notNull(enderecoDTO.getRua(), "Rua não pode ser nula");
        Assert.notNull(enderecoDTO.getNuEndereco(), "Numero do endereço não pode ser nulo");
        Assert.notNull(enderecoDTO.getBairro(), "Bairro não pode ser nulo");
        Assert.notNull(enderecoDTO.getCliente(), "Cliente não pode ser nulo");
        Endereco endereco = convertToEntity(enderecoDTO);

        this.enderecoRepository.save(endereco);
    }

    public boolean deletar(Long id)
    {
        return true;
    }

    private EnderecoDTO convertToDTO(Endereco endereco)
    {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setNuEndereco(endereco.getNuEndereco());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setCliente(convertClienteToDTO(endereco.getCliente()));

        return enderecoDTO;
    }

    private Endereco convertToEntity(EnderecoDTO enderecoDTO)
    {
        Endereco endereco = new Endereco();

        endereco.setNuEndereco(enderecoDTO.getNuEndereco());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setCliente(convertClienteToEntity(enderecoDTO.getCliente()));

        return endereco;
    }

    private ClienteDTO convertClienteToDTO(Cliente cliente)
    {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setTelCelular(cliente.getTelCelular());

        return clienteDTO;
    }

    private Cliente convertClienteToEntity(ClienteDTO clienteDTO)
    {
        Cliente cliente = new Cliente();

        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelCelular(clienteDTO.getTelCelular());

        return cliente;
    }
}
