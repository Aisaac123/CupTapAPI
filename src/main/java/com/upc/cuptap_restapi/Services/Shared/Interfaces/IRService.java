package com.upc.cuptap_restapi.Services.Shared.Interfaces;


import com.upc.cuptap_restapi.Models.Interfaces.Entities.ReadEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Services.Utils.Options.ParamOptions;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * Interface of Reading Services and implement interfaces {@link Services}
 */
public interface IRService<E extends ReadEntity, ID extends Comparable<ID>> extends Services {
    Response<List> GetAll(ParamOptions options);
    Response<List<E>> GetAll();
    Response GetById(ID id, ParamOptions options);

    Response GetById(ID id);


    Response<Page> GetPages(ParamOptions options);
}
