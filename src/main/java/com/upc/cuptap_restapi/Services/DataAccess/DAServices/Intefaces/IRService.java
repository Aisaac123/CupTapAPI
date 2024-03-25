package com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces;


import com.upc.cuptap_restapi.Models.Interfaces.ReadEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRService<E extends ReadEntity, ID extends Comparable<ID>> extends Services {
    Response<List<E>> GetAll();

    Response<E> GetById(ID id);

    Response<Page<E>> GetPages(int page_index, int limit);

}