package com.example.pizzaria.repository;

import com.example.pizzaria.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
