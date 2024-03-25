package com.upc.cuptap_restapi.Services.General.GenericServices;

import com.upc.cuptap_restapi.Models.Interface.DeleteEnity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Repository.Repositories.GlobalRepository;
import com.upc.cuptap_restapi.Services.General.GenericServices.Intefaces.IDService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class DService<E extends DeleteEnity, ID extends Comparable<ID>> implements IDService<E, ID> {

    private GlobalRepository<E, ID> repository;

    public DService<E, ID> setRepository(GlobalRepository<E, ID> repository) {
        this.repository = repository;
        return this;
    }

    @Override
    public Response<E> Delete(ID id) {
        try {
            E old = repository.findById(id).orElse(null);
            repository.deleteById(id);
            return new ResponseBuilder<E>().withData(old).withMsg("Se ha eliminado exitosamente");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<E> Delete(E entity) {
        try {
            repository.delete(entity);
            return new ResponseBuilder<E>().withData(entity).withMsg("Se ha eliminado exitosamente");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<List<E>> Delete(Iterable<ID> iterable) {
        try {
            List<E> list = repository.findAllById(iterable);
            repository.deleteAll(list);
            return new ResponseBuilder<List<E>>().withData(list).withMsg("Se ha eliminado exitosamente");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<List<E>> DeleteAll() {
        try {
            var list = repository.findAll();
            repository.deleteAll();
            return new ResponseBuilder<List<E>>().withData(list).withMsg("Se ha eliminado exitosamente");
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
}
