package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    Set<Producto> findByIds(@Param("nombres") Set<String> nombres);

    default Set<Producto> findByIdsByDetails(Set<DetallesPedido> detallles) {
        Set<String> ids = detallles.stream().map(DetallesPedido::getProducto).map(Producto::getNombre).collect(Collectors.toSet());
        return findByIds(ids);
    }
    @Modifying
    @Query("UPDATE Productos p SET p.stock = :newQuantity WHERE p.nombre = :nombre")
    void updateProductoCantidad(@Param("nombre") String nombre, @Param("newQuantity") Integer newQuantity);
    @Modifying
    @Query("UPDATE Productos p SET p.stock = p.stock + :addQuantity WHERE p.nombre = :nombre")
    void sumProductoCantidad(@Param("nombre") String nombre, @Param("addQuantity") Integer addQuantity);
}