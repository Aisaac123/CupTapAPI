package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.DetallesPedido}
 */
public interface DetallesPedidoDAO extends GlobalRepository<DetallesPedido, Long> {
    @Query(value = "SELECT CASE WHEN p.stock >= :cantidad THEN TRUE ELSE FALSE END AS isStockSufficient " +
            "FROM Productos p " +
            "WHERE p.nombre = :nombreProducto")
    boolean hasStockForPedido(@Param("nombreProducto") String nombreProducto, @Param("cantidad") int cantidad);

    @Query(value = "SELECT CASE WHEN c.stock >= :cantidad THEN TRUE ELSE FALSE END AS isStockSufficient " +
            "FROM Combos c " +
            "WHERE c.nombre = :nombreCombo")
    boolean hasStockForCombos(@Param("nombreCombo") String nombreCombo, @Param("cantidad") int cantidad);
}