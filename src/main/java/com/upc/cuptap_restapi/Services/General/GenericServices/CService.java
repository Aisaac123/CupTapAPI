package com.upc.cuptap_restapi.Services.General.GenericServices;

import com.upc.cuptap_restapi.Models.Interface.CreateEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Repository.Repositories.GlobalRepository;
import com.upc.cuptap_restapi.Services.General.GenericServices.Intefaces.ICService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Setter(value = AccessLevel.PROTECTED)
public class CService<E extends CreateEntity, ID extends Comparable<ID>> implements ICService<E> {


    private GlobalRepository<E, ID> repository;

    private static CService<?, ?> _instance;
    public static <E extends CreateEntity, ID extends Comparable<ID>> CService<E, ID> GetInstance(){
        if (_instance == null) _instance = new CService<E, ID>();
        return (CService<E, ID>) _instance;
    }

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
