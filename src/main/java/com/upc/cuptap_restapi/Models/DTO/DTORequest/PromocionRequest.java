package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Entities.Promocion;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Promocion}
 */
public record PromocionRequest(@NotNull @NotEmpty @NotBlank String nombre, @NotEmpty @NotBlank String descripcion,
                               @NotNull @FutureOrPresent LocalDateTime fecha_inicio,
                               @NotNull @Future LocalDateTime fecha_fin, @Positive int descuento,
                               @Size(min = 1) Set<ProductoDTOPromocion> productos,
                               @Size(min = 1) Set<ComboDTOPromocion> combos) implements Serializable, RequestDTO<Promocion> {
    @Override
    public Promocion toEntity() {
        var promocion = new Promocion(nombre, descripcion, fecha_inicio, fecha_fin, descuento);
        Set<Producto> p;
        Set<Combo> c;
        if (productos != null && !productos.isEmpty()) {
            p = productos.stream().map(ProductoDTOPromocion::toEntity).collect(Collectors.toSet());
            promocion.AddProducto(p);
        }
        if (combos != null && !combos.isEmpty()) {
            c = combos.stream().map(ComboDTOPromocion::toEntity).collect(Collectors.toSet());
            promocion.AddCombos(c);
        }
        return promocion;
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Producto}
     */
    public record ProductoDTOPromocion(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable, RequestDTO<Producto> {

        @Override
        public Producto toEntity() {
            return new Producto(nombre);
        }
    }

    /**
     * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Combo}
     */
    public record ComboDTOPromocion(@NotNull @NotEmpty @NotBlank String nombre) implements Serializable, RequestDTO<Combo> {
        @Override
        public Combo toEntity() {
            return new Combo(nombre);
        }
    }
}