package com.example.pizzaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@NotBlank(message = "O campo não pode ser vazio")
@NotNull(message = "O campo não pode ser nulo")
public class PizzaDTO extends AbstractEntityDTO{
    private PizzaTipoDTO tipoDTO;
    private SaborDTO saborDTO;

}
