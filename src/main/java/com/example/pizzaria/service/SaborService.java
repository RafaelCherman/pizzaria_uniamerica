package com.example.pizzaria.service;

import com.example.pizzaria.dto.ProdutoDiversoDTO;
import com.example.pizzaria.dto.SaborDTO;
import com.example.pizzaria.entity.ProdutoDiverso;
import com.example.pizzaria.entity.Sabor;
import com.example.pizzaria.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaborService {

    @Autowired
    private SaborRepository saborRepository;

    public void cadastrar(SaborDTO saborDTO) {
        if (this.saborRepository.existsByNome(saborDTO.getNome())) {
            throw new RuntimeException("Sabor já cadastrado");
        }
        Sabor sabor = convertToEntity(saborDTO);
        this.saborRepository.save(sabor);
    }

    public String editar(SaborDTO saborDTO, Long id) {
        Long idFront = id;
        if (saborDTO.getId() != idFront) {
            throw new RuntimeException("Os IDs não coincidem");
        } else if (saborRepository.findByNome(saborDTO.getNome()).getId() != idFront) {
            throw new RuntimeException("Sabor já cadastrado");
        }
        Sabor sabor = convertToEntity(saborDTO);
        this.saborRepository.save(sabor);
        return "Sabor editado com sucesso";
    }


    public Sabor convertToEntity(SaborDTO saborDTO) {
        Sabor sabor = new Sabor();
        sabor.setSabor(saborDTO.getNome());
        sabor.setValor(saborDTO.getValor());
        sabor.setIngredientes(saborDTO.getIngredientes());
        return sabor;
    }

    public List<SaborDTO> findAll() {
        List<Sabor> sabores = this.saborRepository.findAll();
        if(sabores.isEmpty()){
            throw new RuntimeException("Não há sabores cadastrados");
        }else{
        List<SaborDTO> saboresDTO = new ArrayList<>();
        for (Sabor i : sabores
        ) {
            saboresDTO.add(convertToDTO(i));
        }
        return saboresDTO;

    }}

    public SaborDTO findById(Long id) {
        Sabor sabor = this.saborRepository.findById(id).orElse(null);
        return sabor == null
                ? null
                : convertToDTO(sabor);
    }

    public String deletar(Long id){
        if(saborRepository.saborExistTb_pizza(id)){
            Optional<Sabor> sabor = this.saborRepository.findById(id);
            sabor.get().setAtivo(false);
            throw new RuntimeException("Sabor foi inativado, pois está sendo usado em uma pizza");
        }
        else{
            this.saborRepository.deleteById(id);
            return "Sabor deletado com sucesso";
        }
    }

    public SaborDTO convertToDTO(Sabor sabor) {
        SaborDTO saborDTO = new SaborDTO();
        saborDTO.setNome(sabor.getSabor());
        saborDTO.setValor(sabor.getValor());
        saborDTO.setIngredientes(sabor.getIngredientes());
        saborDTO.setId(sabor.getId());
        return saborDTO;


    }
}
