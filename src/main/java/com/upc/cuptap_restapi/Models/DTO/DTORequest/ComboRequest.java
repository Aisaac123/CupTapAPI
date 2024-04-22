package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Combo}
 */


public record ComboRequest(@NotNull @NotEmpty @NotBlank String nombre, String descripcion,
                           @NotNull @PositiveOrZero double precio,
                           @PositiveOrZero int stock, boolean venta_activa, Long promocionId)

        implements Serializable, RequestDTO<Combo> {
    @Override
    public Combo toEntity() {
        if (promocionId == null) return new Combo(nombre, descripcion, precio, stock, venta_activa);

        return new Combo(nombre, descripcion, precio, stock, venta_activa, new Promocion(promocionId));
    }
}

