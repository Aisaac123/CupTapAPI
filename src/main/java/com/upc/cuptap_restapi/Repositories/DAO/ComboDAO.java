package com.upc.cuptap_restapi.Repositories.DAO;

import com.upc.cuptap_restapi.Models.Entities.Combo;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Repository of {@link com.upc.cuptap_restapi.Models.Entities.Combo}
 */
public interface ComboDAO extends GlobalRepository<Combo, String> {

    @Query("SELECT c FROM Combos c WHERE c.nombre IN :nombres")
    Map<String, Combo> findByIds(@Param("nombres") Set<String> nombres);

}