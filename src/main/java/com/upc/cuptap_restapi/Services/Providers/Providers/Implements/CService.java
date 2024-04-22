package com.upc.cuptap_restapi.Services.Providers.Providers.Implements;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import com.upc.cuptap_restapi.Services.Providers.Providers.Interfaces.ICService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * General Creating Service, use {@link GlobalRepository} and implement interfaces {@link ICService}
 */
@NoArgsConstructor
@AllArgsConstructor
@Service
@Setter(value = AccessLevel.PROTECTED)
public class CService<E extends CreateEntity, ID extends Comparable<ID>> implements ICService<E> {


    private GlobalRepository<E, ID> repository;

    public CService<E, ID> setRepository(GlobalRepository<E, ID> repository) {
        this.repository = repository;
        return this;
    }

    @Override
    public Response<E> Save(E entity) {
        try {
            E data = repository.save(entity);
            return new ResponseBuilder<E>().withData(data);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<List<E>> Save(Iterable<E> iterable) {
        try {
            List<E> iter = repository.saveAll(iterable);
            return new ResponseBuilder<List<E>>().withData(iter);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
}
