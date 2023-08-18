package com.example.pizzaria.service;

import com.example.pizzaria.dto.PizzaTipoDTO;
import com.example.pizzaria.dto.ProdutoDiversoDTO;
import com.example.pizzaria.entity.PizzaTipo;
import com.example.pizzaria.entity.ProdutoDiverso;
import com.example.pizzaria.repository.PizzaTipoRepository;
import com.example.pizzaria.repository.ProdutoDiversoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaTipoService {

    @Autowired
    private PizzaTipoRepository pizzaTipoRepository;

    public void cadastrar(PizzaTipoDTO pizzaTipoDTO) {
        Assert.notNull(pizzaTipoDTO.getTamanho(), "Tipo nao pode ser Nulo");
        Assert.notNull(pizzaTipoDTO.getNome(), "Nome nao pode ser Nulo");
        Assert.notNull(pizzaTipoDTO.getValor(), "Preco nao pode ser Nulo");

        PizzaTipo salvarEmBanco = convertToEntity(pizzaTipoDTO);
        this.pizzaTipoRepository.save(salvarEmBanco);

    }

    public void editar(PizzaTipoDTO pizzaTipoDTO, Long id) {

        Assert.notNull(pizzaTipoDTO.getNome(), "Nome nao pode ser Nulo");
        Assert.notNull(pizzaTipoDTO.getValor(), "Preco nao pode ser Nulo");
        Assert.notNull(pizzaTipoDTO.getTamanho(), "Quantidade nao pode ser Nulo");

        PizzaTipo pizzaTipo = convertToEntity(pizzaTipoDTO);
        this.pizzaTipoRepository.save(pizzaTipo);
    }

    public PizzaTipoDTO findById(Long id) {
        PizzaTipo pizzaTipo = this.pizzaTipoRepository.findById(id).orElse(null);
        return pizzaTipo == null
                ? null
                : convertToDTO(pizzaTipo);
    }




    private PizzaTipo convertToEntity(PizzaTipoDTO pizzaTipoDTO) {
        PizzaTipo pizzaTipo = new PizzaTipo();
        pizzaTipo.setTamanho(pizzaTipoDTO.getTamanho());
        pizzaTipo.setNome(pizzaTipoDTO.getNome());
        pizzaTipo.setValor(pizzaTipoDTO.getValor());

        return pizzaTipo;
    }

    private PizzaTipoDTO convertToDTO(PizzaTipo pizzaTipo) {
        PizzaTipoDTO pizzaTipoDTO = new PizzaTipoDTO();
        pizzaTipoDTO.setTamanho(pizzaTipo.getTamanho());
        pizzaTipoDTO.setNome(pizzaTipo.getNome());
        pizzaTipoDTO.setValor(pizzaTipo.getValor());

        return pizzaTipoDTO;
    }


    public List<PizzaTipoDTO> findAll() {
        List<PizzaTipo> pizzaTipos = this.pizzaTipoRepository.findAll();
        List<PizzaTipoDTO> pizzaTipoDTO = new ArrayList<>();
        for (PizzaTipo i: pizzaTipos) {

            pizzaTipoDTO.add(convertToDTO(i));
        }

        return pizzaTipoDTO;



    }



}
