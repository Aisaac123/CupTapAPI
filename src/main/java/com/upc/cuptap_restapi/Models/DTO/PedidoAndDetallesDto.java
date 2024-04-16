package com.upc.cuptap_restapi.Models.DTO;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
 */


public record PedidoAndDetallesDto(String usuarioCedula,
                                   @NotNull List<DetallesPedidoDto> detalles)

                                    implements Serializable, IDTO<Pedido> {
    @Override
    public Pedido toEntity() {

        var list = new ArrayList<DetallesPedido>();
        for (var detalle: detalles) {
            DetallesPedido detallesPedido = detalle.toEntity();
            list.add(detallesPedido);
        }

        return new Pedido(new Usuario(usuarioCedula), list);
    }
}