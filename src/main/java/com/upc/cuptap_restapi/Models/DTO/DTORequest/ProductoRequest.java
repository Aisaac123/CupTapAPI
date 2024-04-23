package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Producto}
 */
public record ProductoRequest(@NotNull @NotEmpty @NotBlank String nombre, @NotEmpty @NotBlank String descripcion,
                              @Positive double precio, @PositiveOrZero int stock, boolean ventaActiva,
                              @NotNull PromocionDTOProducto promocion) implements Serializable, RequestDTO<Producto> {
    @Override
    public Producto toEntity() {
        if (promocion == null || promocion.id == null)
            return new Producto(nombre, descripcion, precio, stock, ventaActiva);

        return new Producto(nombre, descripcion, precio, stock, ventaActiva, new com.upc.cuptap_restapi.Models.Entities.Promocion(promocion.id()));
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Promocion}
     */
    public record PromocionDTOProducto(@NotNull @Positive Long id) implements Serializable {
    }
}