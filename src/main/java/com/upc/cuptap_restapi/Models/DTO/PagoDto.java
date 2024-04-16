package com.upc.cuptap_restapi.Models.DTO;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.Pago;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Pago}
 */


public record PagoDto(double valor, @NotNull String usuarioCedula)  implements Serializable, IDTO<Pago> {
    @Override
    public Pago toEntity() {
        return new Pago(valor, new Usuario(usuarioCedula));
    }
}