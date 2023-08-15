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
        Assert.notNull(funcionarioDTO.getDs_funcao(), "Função não pode ser nula");
        Assert.notNull(funcionarioDTO.getNm_funcionario(), "Nome não pode ser nulo");
        Assert.notNull(funcionarioDTO.getNu_cpf_funcionario(), "CPF não pode ser nulo");
        Assert.isTrue(!(this.funcionarioRepository.alreadyExists(funcionarioDTO.getNu_cpf_funcionario())), "CPF já cadastrado");

        Funcionario funcionario = convertToEntity(funcionarioDTO);

        this.funcionarioRepository.save(funcionario);
    }

    public void editar(FuncionarioDTO funcionarioDTO, Long id)
    {
        Assert.isTrue(funcionarioRepository.doesExist(id), "Funcionario não existe");
        Assert.notNull(funcionarioDTO.getDs_funcao(), "Função não pode ser nula");
        Assert.notNull(funcionarioDTO.getNm_funcionario(), "Nome não pode ser nulo");
        Assert.notNull(funcionarioDTO.getNu_cpf_funcionario(), "CPF não pode ser nulo");
        if(this.funcionarioRepository.alreadyExists(funcionarioDTO.getNu_cpf_funcionario()))
        {
            Assert.isTrue( this.funcionarioRepository.isTheSame(funcionarioDTO.getNu_cpf_funcionario()).equals(id) ,"Ja existe");
        }

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
