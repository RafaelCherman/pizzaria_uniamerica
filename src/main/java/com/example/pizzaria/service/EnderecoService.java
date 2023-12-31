package com.example.pizzaria.service;

import com.example.pizzaria.dto.ClienteDTO;
import com.example.pizzaria.dto.EnderecoDTO;
import com.example.pizzaria.entity.Cliente;
import com.example.pizzaria.entity.Endereco;
import com.example.pizzaria.repository.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<EnderecoDTO> findAll()
    {
        List<Endereco> enderecos = this.enderecoRepository.findAll();
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();

        for(Endereco i : enderecos)
        {
            enderecosDTO.add(modelMapper.map(i, EnderecoDTO.class));
        }

        return enderecosDTO;
    }

    public EnderecoDTO findById(Long id)
    {
        Endereco endereco = this.enderecoRepository.findById(id).orElseThrow(()-> new RuntimeException("Registro não encontrado"));

        return modelMapper.map(endereco, EnderecoDTO.class);
    }

    public void cadastrar(EnderecoDTO enderecoDTO)
    {
        this.enderecoRepository.save(modelMapper.map(enderecoDTO, Endereco.class));
    }

    public void editar(EnderecoDTO enderecoDTO, Long id)
    {
        Endereco endereco = this.enderecoRepository.findById(id).orElseThrow(()-> new RuntimeException("Registro não encontrado"));

        modelMapper.map(enderecoDTO,endereco);
        this.enderecoRepository.save(endereco);
    }

    public boolean deletar(Long id)
    {
        Endereco endereco = this.enderecoRepository.findById(id).orElseThrow(()-> new RuntimeException("Registro não encontrado"));
        endereco.setAtivo(false);
        this.enderecoRepository.save(endereco);
        return true;
    }


}
