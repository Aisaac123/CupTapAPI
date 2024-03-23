package com.upc.cuptap_restapi.Services.General.GenericServices;

import com.upc.cuptap_restapi.Models.Interface.ReadEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Repository.Repositories.GlobalRepository;
import com.upc.cuptap_restapi.Services.General.GenericServices.Intefaces.IRService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class RService<E extends ReadEntity, ID extends Comparable<ID>> implements IRService<E, ID> {

    private GlobalRepository<E, ID> repository;

    public RService() {
        _instance = this;
    }

    private static RService<?, ?> _instance;
    public static <E extends ReadEntity, ID extends Comparable<ID>> RService<E, ID> GetInstance(){
        if (_instance == null) _instance = new RService<E, ID>();
        return (RService<E, ID>) _instance;
    }

    public RService<E, ID> setRepository(GlobalRepository<E, ID> repository) {
        this.repository = repository;
        return this;
    }
    @Override
    public Response<List<E>> GetAll() {
        try {
            List<E> list = repository.findAll();
            return new ResponseBuilder<List<E>>().withSuccess(true).withData(list);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<E> GetById(ID id) {
        try {
            E data = repository.findById(id).orElse(null);
            return new ResponseBuilder<E>().withSuccess(true).withData(data);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<Page<E>> GetPages(int page_index, int limit) {
        try {
            PageRequest pageable = PageRequest.of(page_index, limit);
            Page<E> _page = repository.findAll(pageable);
            return new ResponseBuilder<Page<E>>().withSuccess(true).withData(_page);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
}
