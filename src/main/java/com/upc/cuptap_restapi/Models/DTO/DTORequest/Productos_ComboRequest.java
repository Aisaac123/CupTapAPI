package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Entities.Productos_Combo;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Productos_Combo}
 */


public record Productos_ComboRequest(@Positive int cantidad, String productoNombre,
                                     String comboNombre)

        implements Serializable, RequestDTO<Productos_Combo> {
    @Override
    public Productos_Combo toEntity() {
        return new Productos_Combo(cantidad, new Producto(productoNombre), new Combo(comboNombre));
    }
}