package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;

import java.util.List;
import java.util.UUID;


/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.Usuario}
 */
public interface UsuarioDAO extends GlobalRepository<Usuario, UUID> {
    List<Usuario> findAllByNombre(String nombre);
    Usuario findByCedula(String cedula);

}