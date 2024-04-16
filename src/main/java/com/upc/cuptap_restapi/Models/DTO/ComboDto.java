package com.upc.cuptap_restapi.Models.DTO;

import com.upc.cuptap_restapi.Models.Entities.Admin;
import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Combo}
 */


public record ComboDto(@NotNull @NotEmpty @NotBlank String nombre, String descripcion,
                       @NotNull @PositiveOrZero double precio,
                       @PositiveOrZero int stock, boolean venta_activa, Long promocionId)

                        implements Serializable, IDTO<Combo> {
    @Override
    public Combo toEntity() {
        return new Combo(nombre, descripcion, precio, stock, venta_activa, new Promocion(promocionId));
    }
}
