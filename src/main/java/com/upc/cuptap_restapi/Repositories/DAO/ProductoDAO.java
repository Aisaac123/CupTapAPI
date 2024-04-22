package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.Producto}
 */
public interface ProductoDAO extends GlobalRepository<Producto, String> {

    @Query("SELECT p FROM Productos p WHERE p.nombre IN :nombres")
    Map<String, Producto> findByIds(@Param("nombres") Set<String> nombres);
}