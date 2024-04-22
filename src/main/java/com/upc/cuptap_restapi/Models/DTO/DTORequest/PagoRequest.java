package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Pago;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Pago}
 */


public record PagoRequest(double valor, @NotNull String usuarioCedula) implements Serializable, RequestDTO<Pago> {
    @Override
    public Pago toEntity() {
        return new Pago(valor, new Usuario(usuarioCedula));
    }
}