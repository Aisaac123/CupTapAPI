package com.upc.cuptap_restapi.Services.Shared.Implements;

import com.upc.cuptap_restapi.Events.Interfaces.DataBaseEventListener;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.DeleteEnity;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.HasSocketChannel;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import com.upc.cuptap_restapi.Services.Shared.Interfaces.IDService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * General Deleting Service, use {@link GlobalRepository} and implement interfaces {@link IDService}
 */
@NoArgsConstructor
@AllArgsConstructor
@Service
public class DService<E extends DeleteEnity, ID extends Comparable<ID>> implements IDService<E, ID> {

    private GlobalRepository<E, ID> repository;


    private DataBaseEventListener dataBaseListener;


    public DService<E, ID> setRepository(GlobalRepository<E, ID> repository) {
        this.repository = repository;
        return this;
    }

    public DService(GlobalRepository<E, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Response<E> Delete(ID id, boolean transmit) {
        try {

            E old = repository.findById(id).orElse(null);

            if (old == null) return ResponseBuilder.Fail("No se ha encontrado el item a eliminar");
            repository.deleteById(id);

            if(old instanceof HasSocketChannel<?> && transmit)
                dataBaseListener.handleDelete(old);
            return new ResponseBuilder<E>().withData(old).withMsg("Se ha eliminado exitosamente");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<E> Delete(E entity, boolean transmit) {
        try {
            repository.delete(entity);
            if(entity instanceof HasSocketChannel<?> && transmit)
                dataBaseListener.handleDelete(entity);
            return new ResponseBuilder<E>().withData(entity).withMsg("Se ha eliminado exitosamente");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<List<E>> Delete(Iterable<ID> iterable, boolean transmit) {
        try {
            List<E> list = repository.findAllById(iterable);
            repository.deleteAll(list);
            for (var item : iterable) {
                if (item instanceof HasSocketChannel<?> && transmit)
                    dataBaseListener.handleDelete(iterable);
                break;
            }
            return new ResponseBuilder<List<E>>().withData(list).withMsg("Se ha eliminado exitosamente");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<List<E>> DeleteAll(boolean transmit) {
        try {
            var list = repository.findAll();
            repository.deleteAll();
            for (var item : list) {
                if (item instanceof HasSocketChannel<?> && transmit)
                    dataBaseListener.handleDelete(list);
                break;
            }
            return new ResponseBuilder<List<E>>().withData(list).withMsg("Se ha eliminado exitosamente");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
}
