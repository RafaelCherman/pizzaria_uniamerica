package com.example.pizzaria.DTO;

import com.example.pizzaria.DTO.AbstractEntityDTO;
import com.example.pizzaria.DTO.PizzaDTO;
import com.example.pizzaria.DTO.PizzaTipoDTO;
import com.example.pizzaria.DTO.SaborDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@NotBlank(message = "O campo não pode ser vazio")
@NotNull(message = "O campo não pode ser nulo")
public class PizzaDTO extends AbstractEntityDTO {
    private PizzaTipoDTO tipoDTO;
    private List<SaborDTO> saborDTO;
    @Min(value = 0, message = "O valor não pode ser negativo")
    private Double valorPizza;

}
