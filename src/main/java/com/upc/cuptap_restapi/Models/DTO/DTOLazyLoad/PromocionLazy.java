package com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad;

import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.LazyDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Promocion}
 */
public record PromocionLazy(Long id, String nombre, String descripcion, LocalDateTime fecha_inicio,
                            LocalDateTime fecha_fin, int descuento,
                            LocalDateTime fechaRegistro) implements Serializable, LazyDTO<Promocion> {
}