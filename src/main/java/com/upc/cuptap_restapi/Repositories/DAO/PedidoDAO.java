package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
 */
public interface PedidoDAO extends GlobalRepository<Pedido, Long> {

    @Query("SELECT SUM(p.total) FROM Pedidos p")
    double sumPrecio();

    @Query("select sum(d.subtotal) from DetallesPedido d where d.pedido.id = :pedidoId")
    double sumPrecioPedido(@Param("pedidoId") Long id);

}