package com.upc.cuptap_restapi.Models.DTO;

import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Usuario}
 */


public record UsuarioDto(@NotNull @NotEmpty @NotBlank String cedula, @NotNull @NotEmpty @NotBlank String nombre,
                         @NotNull @NotEmpty @NotBlank String apellidos, @NotNull @NotEmpty @NotBlank String telefono,
                         String username, String password)

                         implements Serializable, IDTO<Usuario> {
    @Override
    public Usuario toEntity() {
        return new Usuario(cedula, nombre, apellidos, telefono, username, password);
    }
}