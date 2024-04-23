package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Combo}
 */
public record ComboRequest(@NotNull @NotEmpty @NotBlank String nombre, @NotEmpty @NotBlank String descripcion,
                           @Positive double precio, @PositiveOrZero int stock, boolean ventaActiva,
                           PromocionDTOComboRequest promocion) implements Serializable, RequestDTO<Combo> {


    @Override
    public Combo toEntity() {
        if (promocion == null || promocion.id == null)
            return new Combo(nombre, descripcion, precio, stock, ventaActiva);

        return new Combo(nombre, descripcion, precio, stock, ventaActiva, new com.upc.cuptap_restapi.Models.Entities.Promocion(promocion().id()));
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Promocion}
     */
    public record PromocionDTOComboRequest(@NotNull @Positive Long id) implements Serializable {
    }
}