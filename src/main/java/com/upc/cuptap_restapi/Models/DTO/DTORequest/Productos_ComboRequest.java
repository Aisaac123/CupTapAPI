package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Productos_Combo;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Productos_Combo}
 */
public record Productos_ComboRequest(@Positive int cantidad, @NotNull ProductoDTOProductos_Combo producto,
                                     @NotNull ComboDTOProductos_Combo combo) implements Serializable, RequestDTO<Productos_Combo> {
    @Override
    public Productos_Combo toEntity() {
        return new Productos_Combo(cantidad, new com.upc.cuptap_restapi.Models.Entities.Producto(producto().nombre()), new com.upc.cuptap_restapi.Models.Entities.Combo(combo().nombre()));
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Producto}
     */
    public record ProductoDTOProductos_Combo(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable {
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Combo}
     */
    public record ComboDTOProductos_Combo(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable {
    }
}