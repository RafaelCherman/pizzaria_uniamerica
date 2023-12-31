package com.example.pizzaria.service;

import com.example.pizzaria.dto.PizzaDTO;
import com.example.pizzaria.dto.SaborDTO;
import com.example.pizzaria.entity.Pizza;
import com.example.pizzaria.entity.Sabor;
import com.example.pizzaria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    SaborService saborService;
    @Autowired
    PizzaTipoService tipoService;



    public PizzaDTO convertToDTO(Pizza pizza) {
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(pizza.getId());
        List<SaborDTO> sabores = new ArrayList<SaborDTO>();
        for (Sabor i: pizza.getSabor()
             ) {
            sabores.add(saborService.convertToDTO(i));

        }
        pizzaDTO.setSaborDTO(sabores);
        pizzaDTO.setTipoDTO(tipoService.convertToDTO(pizza.getTipo()));
        return pizzaDTO;
    }

    public Pizza convertToEntity(PizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza();
        List<Sabor> sabores = new ArrayList<Sabor>();
        for (SaborDTO i: pizzaDTO.getSaborDTO()
             ) {
            sabores.add(saborService.convertToEntity(i));
        }
        pizza.setSabor(sabores);
        pizza.setTipo(tipoService.convertToEntity(pizzaDTO.getTipoDTO()));
        return pizza;
    }


    public PizzaDTO findById(Long id) {

        return this.convertToDTO(pizzaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Registro não encontrado")));
    }

    public List<PizzaDTO> findAll() {
        List<Pizza> pizzas = this.pizzaRepository.findAll();
        if (pizzas.isEmpty()) {
            throw new RuntimeException("Não há pizzas cadastradas");
        } else {
            List<PizzaDTO> pizzasDTO = new ArrayList<>();
            for (Pizza i : pizzas
            ) {
                pizzasDTO.add(convertToDTO(i));
            }
            return pizzasDTO;
        }

    }

    public String cadastrar(PizzaDTO pizza) {
        Pizza salvarEmBanco = convertToEntity(pizza);
        this.pizzaRepository.save(salvarEmBanco);
        return "Pizza cadastrada com sucesso";
    }

    public String editar(PizzaDTO pizza, Long id) {
        if (!Objects.equals(pizza.getId(), id)) {
            throw new RuntimeException("Os IDs não coincidem");
        } else if (!pizzaRepository.existsById(id)) {
            throw new RuntimeException("Pizza não encontrada");
        } else {
            Pizza pizzaEditada = convertToEntity(pizza);
            this.pizzaRepository.save(pizzaEditada);
            return "Pizza editada com sucesso";
        }
    }

    public String deletar(Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new RuntimeException("Pizza não encontrada");
        } else {
            this.pizzaRepository.deleteById(id);
            return "Pizza deletada com sucesso";
        }


    }
}
