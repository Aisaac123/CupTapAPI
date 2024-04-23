package com.upc.cuptap_restapi.Models.DTO.DTOLazyLoad;

import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Interfaces.DTO.LazyDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * IDTO for {@link com.upc.cuptap_restapi.Models.Entities.Usuario}
 */
public record UsuarioLazy(UUID id, String cedula, String nombre, String apellidos, String telefono,
                          String username,
                          LocalDateTime fechaRegistro) implements Serializable, LazyDTO<Usuario> {

}