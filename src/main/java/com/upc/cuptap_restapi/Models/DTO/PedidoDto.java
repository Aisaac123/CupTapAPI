package com.upc.cuptap_restapi.Models.DTO;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
 */


public record PedidoDto(String usuarioCedula)  implements Serializable, IDTO<Pedido> {
    @Override
    public Pedido toEntity() {
        return new Pedido(new Usuario(usuarioCedula));
    }
}