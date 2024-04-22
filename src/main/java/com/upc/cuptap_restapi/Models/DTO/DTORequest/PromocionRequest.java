package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Promocion}
 */


public record PromocionRequest(@NotNull @NotEmpty @NotBlank String nombre, String descripcion,
                               @NotNull @FutureOrPresent LocalDateTime fecha_inicio,
                               @Future LocalDateTime fecha_fin,
                               @PositiveOrZero int descuento)

        implements Serializable, RequestDTO<Promocion> {
    @Override
    public Promocion toEntity() {
        return new Promocion(nombre, descripcion, fecha_inicio, fecha_fin, descuento);
    }
}