package com.example.pizzaria.repository;

import com.example.pizzaria.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("select exists (select f from Funcionario f where f.nu_cpf_funcionario = :cpf)")
    boolean alreadyExists(@Param("nome") final String cpf);

    @Query("select f.id from Funcionario f where f.nu_cpf_funcionario = :cpf")
    Long isTheSame(@Param("nome") final String cpf);

    @Query("select exists (select f from Funcionario f where f.id = :id)")
    boolean doesExist(@Param("id") final Long id);
}
