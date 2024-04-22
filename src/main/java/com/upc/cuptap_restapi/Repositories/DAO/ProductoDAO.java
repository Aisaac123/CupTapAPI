package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.Producto}
 */
public interface ProductoDAO extends GlobalRepository<Producto, String> {

    @Query("SELECT p FROM Productos p WHERE p.nombre IN :nombres")
    List<Producto> findByIds(@Param("nombres") Set<String> nombres);

    default List<Producto> findByIdsByDetails(Set<DetallesPedido> detallles) {
        Set<String> ids = detallles.stream().map(DetallesPedido::getProducto).map(Producto::getNombre).collect(Collectors.toSet());
        return findByIds(ids);
    }
}