package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import com.upc.cuptap_restapi.Events.Interfaces.ListeningMethod;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.Pedido}
 */
public interface PedidoDAO extends GlobalRepository<Pedido, Long> {

    @Query("SELECT SUM(p.total) FROM Pedidos p")
    double sumPrecio();

    @Query("select sum(d.subtotal) from DetallesPedido d where d.pedido.id = :pedidoId")
    double sumPrecioPedido(@Param("pedidoId") Long id);

    @Transactional
    @Modifying
    @Query(value = "update pedidos set estado_nombre = :estado where id = :id", nativeQuery = true)
    void changeEstado(@Param("estado") String estado,@Param("id") Long id);

}