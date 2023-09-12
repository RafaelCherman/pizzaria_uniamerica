package com.example.pizzaria.repository;

import com.example.pizzaria.entity.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaborRepository extends JpaRepository<Sabor, Long> {
}
