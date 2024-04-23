package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Usuario}
 */
public record UsuarioRequest(@NotNull @Size(min = 10, max = 10) @NotEmpty @NotBlank String cedula,
                             @NotNull @NotEmpty @NotBlank String nombre, @NotNull @NotEmpty @NotBlank String apellidos,
                             @NotNull @NotEmpty @NotBlank String telefono, @NotNull @NotEmpty @NotBlank String username,
                             @NotNull @NotEmpty @NotBlank String password) implements Serializable, RequestDTO<Usuario> {
    @Override
    public Usuario toEntity() {
        return new Usuario(cedula, nombre, apellidos, telefono, username, password);
    }
}