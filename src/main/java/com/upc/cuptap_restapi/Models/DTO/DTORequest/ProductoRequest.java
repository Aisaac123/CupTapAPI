package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Producto}
 */


public record ProductoRequest(@NotNull @NotEmpty @NotBlank String nombre, String descripcion,
                              @PositiveOrZero double precio,
                              @PositiveOrZero int stock, boolean venta_activa, Long promocionId)

        implements Serializable, RequestDTO<Producto> {
    @Override
    public Producto toEntity() {
        if (promocionId == null) return new Producto(nombre, descripcion, precio, stock, venta_activa);
        return new Producto(nombre, descripcion, precio, stock, venta_activa, new Promocion(promocionId));
    }
}