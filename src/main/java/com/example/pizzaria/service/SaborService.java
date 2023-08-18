package com.example.pizzaria.service;

import com.example.pizzaria.DTO.SaborDTO;
import com.example.pizzaria.entity.Sabor;
import com.example.pizzaria.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaborService {

    @Autowired
    private SaborRepository saborRepository;
    public void cadastrar(SaborDTO saborDTO) {
        Assert.notNull(saborDTO.getNome(), "Nome nao pode ser Nulo");
        Assert.notNull(saborDTO.getValor(), "Preco nao pode ser Nulo");
        Assert.notNull(saborDTO.getIngredientes(), "Ingredientes nao pode ser Nulo");

        Sabor sabor = convertToEntity(saborDTO);
        this.saborRepository.save(sabor);
    }

    public void editar(SaborDTO saborDTO, Long id) {

        Assert.notNull(saborDTO.getNome(), "Nome nao pode ser Nulo");
        Assert.notNull(saborDTO.getValor(), "Preco nao pode ser Nulo");
        Assert.notNull(saborDTO.getIngredientes(), "Ingredientes nao pode ser Nulo");

        Sabor sabor = convertToEntity(saborDTO);
        this.saborRepository.save(sabor);
    }



    private Sabor convertToEntity(SaborDTO saborDTO) {
        Sabor sabor = new Sabor();
        sabor.setSabor(saborDTO.getNome());
        sabor.setValor(saborDTO.getValor());
        sabor.setIngredientes(saborDTO.getIngredientes());
        return sabor;
    }

    public List<SaborDTO> findAll() {
        List<Sabor> sabores = this.saborRepository.findAll();
        List<SaborDTO> saboresDTO = new ArrayList<>();
        for (Sabor i: sabores
             ) {
            saboresDTO.add(convertToDTO(i));
        }
        return saboresDTO;

    }


    private SaborDTO convertToDTO(Sabor sabor) {
        SaborDTO saborDTO = new SaborDTO();
        saborDTO.setNome(sabor.getSabor());
        saborDTO.setValor(sabor.getValor());
        saborDTO.setIngredientes(sabor.getIngredientes());
        saborDTO.setId(sabor.getId());
        return saborDTO;


    }
}
