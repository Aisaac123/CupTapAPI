package com.upc.cuptap_restapi.Services.DataAccess.DAServices.Implements;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces.IRService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


/**
 *  General Reading Service, use {@link GlobalRepository} and implement interfaces {@link IRService}
 */
@Service
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RService<E extends ReadEntity, ID extends Comparable<ID>> implements IRService<E, ID> {

    private GlobalRepository<E, ID> repository;


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
            PageRequest pageable = PageRequest.of(page_index - 1, limit);
            Page<E> _page = repository.findAll(pageable);
            return new ResponseBuilder<Page<E>>().withSuccess(true).withData(_page);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
    @Override
    public Response<Page<E>> GetPages(int page_index, int limit, LocalDate fecha) {
        try {
            if (fecha == null) return GetPages(page_index, limit);
            PageRequest pageable = PageRequest.of(page_index - 1, limit);
            Page<E> _page = repository.findAllByFechaRegistro(pageable, fecha.atStartOfDay());
            return new ResponseBuilder<Page<E>>().withSuccess(true).withData(_page);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
}
