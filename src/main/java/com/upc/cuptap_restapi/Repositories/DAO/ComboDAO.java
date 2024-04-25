package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Models.Entities.DetallesPedido;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.Combo}
 */
public interface ComboDAO extends GlobalRepository<Combo, String> {

    @Query("SELECT c FROM Combos c WHERE c.nombre IN :nombres")
    Set<Combo> findByIds(@Param("nombres") Set<String> nombres);

    default Set<Combo> findByIdsByDetails(Set<DetallesPedido> detallles) {
        Set<String> ids = detallles.stream().map(DetallesPedido::getCombo).map(Combo::getNombre).collect(Collectors.toSet());
        return findByIds(ids);
    }
}