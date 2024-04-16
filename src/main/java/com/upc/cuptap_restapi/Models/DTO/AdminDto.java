package com.upc.cuptap_restapi.Models.DTO;

import com.upc.cuptap_restapi.Models.Entities.Admin;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.IDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Admin}
 */


public record AdminDto(@NotNull @Size(min = 10, max = 10) @NotEmpty @NotBlank String cedula,
                       @NotNull @NotEmpty @NotBlank String username,
                       @NotNull @NotEmpty @NotBlank String password) implements Serializable, IDTO<Admin> {
    @Override
    public Admin toEntity() {
        return new Admin(cedula, username, password);
    }
}