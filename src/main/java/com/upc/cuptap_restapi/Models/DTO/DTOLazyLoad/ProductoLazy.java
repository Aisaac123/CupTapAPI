package com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad;

import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.LazyDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Producto}
 */
public record ProductoLazy(String nombre, String descripcion, double precio, int stock, boolean venta_activa,
                           Promocion promocion, byte[] imagen,
                           LocalDateTime fechaRegistro) implements Serializable, LazyDTO<Producto> {
    /**
     * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Promocion}
     */
    public record Promocion(Long id, String nombre, String descripcion, LocalDateTime fecha_inicio,
                            LocalDateTime fecha_fin, int descuento) implements Serializable {
    }
}