package com.upc.cuptap_restapi.Models.DTO;

import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Promocion}
 */



public record PromocionDto(@NotNull @NotEmpty @NotBlank String nombre, String descripcion,
                           @NotNull @FutureOrPresent LocalDateTime fecha_inicio,
                           @Future LocalDateTime fecha_fin,
                           @PositiveOrZero int descuento)

                            implements Serializable, IDTO<Promocion> {
    @Override
    public Promocion toEntity() {
        return new Promocion(nombre, descripcion, fecha_inicio, fecha_fin, descuento);
    }
}