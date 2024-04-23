package com.upc.cuptap_restapi.Models.DTO.DTORequest;

import com.upc.cuptap_restapi.Models.Entities.Admin;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.RequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.upc.cuptap_restapi.Models.Entities.Admin}
 */
public record LoginRequest(@NotNull @NotEmpty @NotBlank String username,
                           @NotNull @NotEmpty @NotBlank String password) implements Serializable, RequestDTO<Admin> {
    @Override
    public Admin toEntity() {
        return new Admin(username, password);
    }
}