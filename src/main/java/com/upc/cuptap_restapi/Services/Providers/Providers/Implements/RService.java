package com.upc.cuptap_restapi.Services.Providers.Providers.Implements;

import com.upc.cuptap_restapi.Models.Interfaces.DTO.LazyDTO;
import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Repositories.Repository.GlobalRepository;
import com.upc.cuptap_restapi.Services.Providers.Providers.Interfaces.IRService;
import com.upc.cuptap_restapi.Services.Utils.Options.ParamOptions;
import com.upc.cuptap_restapi.Services.Utils.Options.ReadingOptions;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * General Reading Service, use {@link GlobalRepository} and implement interfaces {@link IRService}
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

    List<E> _getAll(ParamOptions options) {
        try {
            return repository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Response<List> GetAll(ParamOptions options) {
        try {
            var readOption = (ReadingOptions) options;
            List<E> list;
            if (readOption.getDateLimit() == null)
                list = _getAll(readOption);
            else
                list = repository.findAllByFechaRegistro(readOption.getDateLimit().atStartOfDay());
            if (readOption.isLazy()) {
                List<LazyDTO> lazyPage = list.stream().map(E::toLazy).collect(Collectors.toList());
                return new ResponseBuilder<List>().withSuccess(true).withData(lazyPage);
            }
            return new ResponseBuilder<List>().withSuccess(true).withData(list);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response<E> GetById(ID id) {
        try {
            E data = repository.findById(id).orElse(null);
            if (data == null) return ResponseBuilder.Fail("No se ha encontrado el elemento en la base de datos");
            return new ResponseBuilder<E>().withSuccess(true).withData(data);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    @Override
    public Response GetById(ID id, ParamOptions options) {
        try {
            var readOption = (ReadingOptions) options;
            E data = repository.findById(id).orElse(null);
            if (readOption.isLazy()) {
                LazyDTO lazy = data.toLazy();
                return new ResponseBuilder<LazyDTO>().withSuccess(true).withData(lazy);
            }
            return new ResponseBuilder<E>().withSuccess(true).withData(data);
        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }

    Page<E> getPages(ParamOptions options) {
        try {
            var readOption = (ReadingOptions) options;
            PageRequest pageable = PageRequest.of(
                    readOption.getPageParams().getIndex(),
                    readOption.getPageParams().getLimit()
            );
            return repository.findAll(pageable);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public Response<Page> GetPages(ParamOptions options) {
        try {
            var readOption = (ReadingOptions) options;
            Page<E> page;
            if (readOption.getDateLimit() == null)
                page = getPages(readOption);
            else {
                PageRequest pageable = PageRequest.of(
                        readOption.getPageParams().getIndex(),
                        readOption.getPageParams().getLimit()
                );
                page = repository.findAllByFechaRegistro(pageable, readOption.getDateLimit().atStartOfDay());
            }
            if (readOption.isLazy()) {
                Page<LazyDTO> lazyPage = page.map(E::toLazy);
                return new ResponseBuilder<Page>().withSuccess(true).withData(lazyPage);
            }
            return new ResponseBuilder<Page>().withSuccess(true).withData(page);

        } catch (Exception e) {
            return ResponseBuilder.Error(e);
        }
    }
}
