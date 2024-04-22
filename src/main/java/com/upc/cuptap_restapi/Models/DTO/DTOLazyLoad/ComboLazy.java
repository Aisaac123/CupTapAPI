package com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.LazyDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Combo}
 */
public record ComboLazy(String nombre, String descripcion, double precio, int stock, boolean venta_activa,
                        Promocion promocion, LocalDateTime fechaRegistro,
                        byte[] imagen) implements Serializable, LazyDTO<Combo> {

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Promocion}
     */
    public record Promocion(Long id, String nombre, String descripcion, LocalDateTime fecha_inicio,
                            LocalDateTime fecha_fin, int descuento) implements Serializable {
    }
}