package com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers;

import com.upc.cuptap_restapi.Controllers.DataAccess.DAInterfaces.ICController;
import com.upc.cuptap_restapi.Models.Interfaces.CreateEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Models.Utilities.ResponseBuilder;
import com.upc.cuptap_restapi.Services.DataAccess.DAServices.Intefaces.ICService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@NoArgsConstructor
@AllArgsConstructor
public class CController<E extends CreateEntity, ID extends Comparable<ID>> implements ICController<E> {

    private ICService<E> service;

    public CController<E, ID> setService(ICService<E> service) {
        this.service = service;
        return this;
    }

    @PostMapping("")
    public ResponseEntity<Response<E>> Save(@RequestBody E item) {
        try {
            var response = service.Save(item);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
