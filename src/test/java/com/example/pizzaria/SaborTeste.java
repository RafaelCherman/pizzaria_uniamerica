package com.example.pizzaria;

import com.example.pizzaria.controller.SaborController;
import com.example.pizzaria.dto.SaborDTO;
import com.example.pizzaria.entity.Sabor;
import com.example.pizzaria.repository.SaborRepository;
import com.example.pizzaria.service.SaborService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SaborTeste {

    @MockBean
    SaborRepository saborRepository;
    @Autowired
    SaborService saborService;
    @Autowired
    SaborController saborController;

    @BeforeEach
    void injectDado() {
        Sabor sabor = new Sabor();
        sabor.setId(1l);
        sabor.setSabor("Calabresa");
        sabor.setValor(10.00);
        sabor.setIngredientes("Calabresa, queijo, molho de tomate");
        List<Sabor> sabores = new ArrayList<>();
        sabores.add(sabor);

        SaborDTO saborDTO = new SaborDTO();
        saborDTO.setId(sabor.getId());
        saborDTO.setValor(sabor.getValor());
        saborDTO.setNome(sabor.getSabor());
        saborDTO.setIngredientes(sabor.getIngredientes());
        List<SaborDTO> saboresDTO = new ArrayList<>();
        saboresDTO.add(saborDTO);

        Mockito.when(saborRepository.findById(sabor.getId())).thenReturn(Optional.of(sabor));
        Mockito.when(saborRepository.findByNome(sabor.getSabor())).thenReturn(sabor);
        Mockito.when(saborRepository.findAll()).thenReturn(sabores);
        Mockito.when(saborRepository.save(sabor)).thenReturn(sabor);
    }

    @Test
    void TesteFindByID() {
        var sabor = saborController.findById(1l);
        Assert.assertEquals(1, sabor.getBody().getId(), 0);
    }

    @Test
    void testeFindAll() {
        var sabores = saborController.findAll();
        Assert.assertEquals(1, sabores.getBody().size(), 0);
    }

    @Test
    void testeCadastrar() {
        SaborDTO saborDTO = new SaborDTO();
        var sabor = saborController.cadastrar(saborDTO);
        Assert.assertEquals(1, sabor.getBody());
    }

    @Test
    void testeAtualizar(){
        SaborDTO saborDTO = new SaborDTO();
        saborDTO.setId(1l);
        saborDTO.setNome("Calabresa");
        saborDTO.setValor(10.00);
        saborDTO.setIngredientes("Calabresa, queijo, molho de tomate");

        var sabor = saborController.editar(1l, saborDTO);
        Assert.assertEquals(200, sabor.getStatusCodeValue());
    }
}
