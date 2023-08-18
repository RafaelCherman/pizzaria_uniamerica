package com.example.pizzaria.controller;

import com.example.pizzaria.DTO.SaborDTO;
import com.example.pizzaria.repository.SaborRepository;
import com.example.pizzaria.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/sabor")
public class SaborController {
    @Autowired
    private SaborRepository saborRepository;
    @Autowired
    private SaborService saborService;

@PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody final SaborDTO saborDTO){
        try{
            this.saborService.cadastrar(saborDTO);

            return ResponseEntity.ok("Cadastrado com sucesso!");
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<String> editar(@RequestParam("id") final Long id, @RequestBody final SaborDTO saborDTO){

        try{
            this.saborService.editar(saborDTO, id);

            return ResponseEntity.ok("Editado com sucesso!");
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("all")
    public ResponseEntity <List<SaborDTO>> findAll(){
        try{
            return ResponseEntity.ok(this.saborService.findAll());
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
