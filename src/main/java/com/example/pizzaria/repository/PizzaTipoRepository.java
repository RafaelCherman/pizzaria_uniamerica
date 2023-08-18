package com.example.pizzaria.repository;

import com.example.pizzaria.entity.PizzaTipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaTipoRepository extends JpaRepository<PizzaTipo, Long> {
}
