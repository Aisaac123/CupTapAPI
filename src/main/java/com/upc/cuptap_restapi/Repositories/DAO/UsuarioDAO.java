package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.Usuario}
 */
public interface UsuarioDAO extends GlobalRepository<Usuario, UUID> {
    List<Usuario> findAllByNombre(String nombre);

    Usuario findByCedula(String cedula);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM Usuarios u WHERE u.username = :username AND u.password = :password")
    boolean existsByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}