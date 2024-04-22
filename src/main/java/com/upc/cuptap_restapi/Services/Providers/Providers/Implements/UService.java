package com.upc.cuptap_restapi.Services.Providers.Providers.Implements;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.UpdateEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import com.upc.cuptap_restapi.Services.Providers.Providers.Interfaces.IUService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * General Update Service, use {@link GlobalRepository} and implement interfaces {@link IUService}
 */

@NoArgsConstructor
@AllArgsConstructor
@Service
public class UService<E extends UpdateEntity, ID extends Comparable<ID>> implements IUService<E, ID> {

    private GlobalRepository<E, ID> repository;

    public UService<E, ID> setRepository(GlobalRepository<E, ID> repository) {
        this.repository = repository;
        return this;
    }

    @Override
    public Response<Map<String, E>> Update(E entity, ID old_id) {
        try {
            Optional<E> option_old = repository.findById(old_id);
            if (option_old.isPresent()) {
                E ent = option_old.get();
                E oldEnt = (E) ent.cloneEntity();
                ent.update(entity);
                HashMap<String, E> map = new HashMap<>();
                map.put("old", oldEnt);
                map.put("new", ent);
                repository.save(ent);
                return new ResponseBuilder<Map<String, E>>().withData(map);
            }
            return ResponseBuilder.Fail("No se ha encontrado el item a actualizar");

        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
}
