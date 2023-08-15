package com.example.pizzaria.service;

import com.example.pizzaria.dto.FuncionarioDTO;
import com.example.pizzaria.entity.Funcionario;
import com.example.pizzaria.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<FuncionarioDTO> findAll(){
        List<Funcionario> funcionarios = this.funcionarioRepository.findAll();
        List<FuncionarioDTO> funcionariosDTO = new ArrayList<>();

        for(Funcionario i : funcionarios)
        {
            funcionariosDTO.add(convertToDTO(i));
        }

        return funcionariosDTO;
    }

    public FuncionarioDTO findById(Long id)
    {
        Funcionario funcionario = this.funcionarioRepository.findById(id).orElse(null);

        return funcionario == null
                ? null
                : convertToDTO(funcionario);
    }

    public void cadastrar(FuncionarioDTO funcionarioDTO)
    {
        Assert.notNull(funcionarioDTO.getDs_funcao(), "Nulo");
        Assert.notNull(funcionarioDTO.getNm_funcionario(), "Nulo");
        Assert.notNull(funcionarioDTO.getNu_cpf_funcionario(), "Nulo");
        //Verificar se cpf é unico

        Funcionario funcionario = convertToEntity(funcionarioDTO);

        this.funcionarioRepository.save(funcionario);
    }

    public void editar(FuncionarioDTO funcionarioDTO, Long id)
    {
        Assert.notNull(funcionarioDTO.getDs_funcao(), "Nulo");
        Assert.notNull(funcionarioDTO.getNm_funcionario(), "Nulo");
        Assert.notNull(funcionarioDTO.getNu_cpf_funcionario(), "Nulo");
        //Verificar se cpf mudou, caso não verificar se é unico

        Funcionario funcionario = convertToEntity(funcionarioDTO);

        this.funcionarioRepository.save(funcionario);
    }

    public boolean deletar(Long id)
    {
        return true;
    }





    private FuncionarioDTO convertToDTO(Funcionario funcionario)
    {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNm_funcionario(funcionario.getNm_funcionario());
        funcionarioDTO.setNu_cpf_funcionario(funcionario.getNu_cpf_funcionario());
        funcionarioDTO.setDs_funcao(funcionario.getDs_funcao());

        return funcionarioDTO;
    }

    private Funcionario convertToEntity(FuncionarioDTO funcionarioDTO)
    {
        Funcionario funcionario = new Funcionario();
        funcionario.setDs_funcao(funcionarioDTO.getDs_funcao());
        funcionario.setNm_funcionario(funcionarioDTO.getNm_funcionario());
        funcionario.setNu_cpf_funcionario(funcionarioDTO.getNu_cpf_funcionario());

        return funcionario;
    }
}
