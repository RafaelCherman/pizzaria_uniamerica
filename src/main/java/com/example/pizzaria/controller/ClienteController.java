package com.example.pizzaria.controller;

import com.example.pizzaria.dto.ClienteDTO;
import com.example.pizzaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "api/cliente")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @GetMapping("all")
    public ResponseEntity<List<ClienteDTO>> findAll()
    {
        try{
            return ResponseEntity.ok(this.clienteService.findAll());
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ClienteDTO> findById(@RequestParam("id") final Long id){

        try{
            return ResponseEntity.ok(this.clienteService.findById(id));
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody final ClienteDTO clienteDTO){

        try{
            this.clienteService.cadastrar(clienteDTO);

            return ResponseEntity.ok("Funcionario cadastrado com sucesso");
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> editar(
            @RequestParam("id") final Long id,
            @RequestBody final ClienteDTO clienteDTO)
    {
        try {
            this.clienteService.editar(clienteDTO, id);

            return ResponseEntity.ok("Cliente alterado com sucesso");
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam("id") final Long id)
    {
        try {
            if(this.clienteService.deletar(id)){
                return ResponseEntity.ok("Cliente desativado");
            }
            else{
                return ResponseEntity.ok("Cliente deletado");
            }
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
