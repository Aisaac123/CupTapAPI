package com.upc.cuptap_restapi.Services.Shared.Implements;

import com.upc.cuptap_restapi.Events.Interfaces.DataBaseEventListener;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.HasSocketChannel;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import com.upc.cuptap_restapi.Services.Shared.Interfaces.ICService;
import com.upc.cuptap_restapi.Events.Event.PedidoEventListener;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * General Creating Service, use {@link GlobalRepository} and implement interfaces {@link ICService}
 */
@NoArgsConstructor
@Service
@Setter(value = AccessLevel.PROTECTED)
public class CService<E extends CreateEntity, ID extends Comparable<ID>> implements ICService<E> {



    private GlobalRepository<E, ID> repository;

    private DataBaseEventListener listener;
    public CService(GlobalRepository<E, ID> repository) {
        this.repository = repository;
    }

    public CService<E, ID> setRepository(GlobalRepository<E, ID> repository) {
        this.repository = repository;
        return this;
    }

    public CService(GlobalRepository<E, ID> repository, PedidoEventListener listener) {
        this.repository = repository;
        this.listener = listener;
    }

    @Override
    public Response<E> Save(E entity, boolean transmit) {
        try {
            var e = repository.save(entity);
            if (entity instanceof HasSocketChannel<?> && transmit){
                listener.handleCreate(repository.findLast());
                }
            return ResponseBuilder.Success("Se ha registrado con exito!");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<List<E>> Save(Iterable<E> iterable, boolean transmit) {
        try {
            List<E> iter = repository.saveAll(iterable);
            for (var item : iterable) {
                if (item instanceof HasSocketChannel<?> && transmit)
                    listener.handleCreate(repository.findLast());
                break;
            }
            return new ResponseBuilder<List<E>>().withData(iter);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

}
