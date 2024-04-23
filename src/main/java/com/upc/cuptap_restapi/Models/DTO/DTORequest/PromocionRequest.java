package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Promocion}
 */
public record PromocionRequest(@NotNull @NotEmpty @NotBlank String nombre, @NotEmpty @NotBlank String descripcion,
                               @NotNull @FutureOrPresent LocalDateTime fecha_inicio,
                               @NotNull @Future LocalDateTime fecha_fin, @Positive int descuento,
                               @NotNull @Size(min = 1) List<ProductoDTOPromocion> productos,
                               @NotNull @Size(min = 1) List<ComboDTOPromocion> combos) implements Serializable, RequestDTO<Promocion> {
    @Override
    public Promocion toEntity() {
        return new Promocion(nombre, descripcion, fecha_inicio, fecha_fin, descuento);
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Producto}
     */
    public record ProductoDTOPromocion(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable {
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Combo}
     */
    public record ComboDTOPromocion(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable {
    }
}