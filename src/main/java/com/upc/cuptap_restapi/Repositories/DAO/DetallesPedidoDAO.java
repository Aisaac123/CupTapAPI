package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.DetallesPedido}
 */
public interface DetallesPedidoDAO extends GlobalRepository<DetallesPedido, Long> {
    @Query(value = "SELECT CASE WHEN p.stock >= :cantidad THEN TRUE ELSE FALSE END AS isStockSufficient " +
            "FROM Productos p")
    boolean HasStockForPedido(int cantidad);

    @Query(value = "SELECT CASE WHEN c.stock >= :cantidad THEN TRUE ELSE FALSE END AS isStockSufficient " +
            "FROM Combos c")
    boolean HasStockForCombos(int cantidad);
}