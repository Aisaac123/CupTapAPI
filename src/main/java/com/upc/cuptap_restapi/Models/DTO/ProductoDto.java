package com.upc.cuptap_restapi.Models.DTO;

import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Producto;
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
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Producto}
 */


public record ProductoDto(@NotNull @NotEmpty @NotBlank String nombre, String descripcion,
                          @PositiveOrZero double precio,
                          @PositiveOrZero int stock, boolean venta_activa, Long promocionId)

                            implements Serializable, IDTO<Producto> {
    @Override
    public Producto toEntity() {
        return new Producto(nombre, descripcion, precio, stock, venta_activa, new Promocion(promocionId));
    }
}