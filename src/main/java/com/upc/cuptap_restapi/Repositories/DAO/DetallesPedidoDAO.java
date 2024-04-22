package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.DetallesPedido}
 */
public interface DetallesPedidoDAO extends GlobalRepository<DetallesPedido, Long> {

}