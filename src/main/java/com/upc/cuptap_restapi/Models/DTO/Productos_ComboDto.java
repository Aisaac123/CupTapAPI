package com.upc.cuptap_restapi.Models.DTO;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Entities.Productos_Combo;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Productos_Combo}
 */


public record Productos_ComboDto(@Positive int cantidad, String productoNombre,
                                 String comboNombre)

                                implements Serializable, IDTO<Productos_Combo> {
    @Override
    public Productos_Combo toEntity() {
        return new Productos_Combo(cantidad, new Producto(productoNombre), new Combo(comboNombre));
    }
}