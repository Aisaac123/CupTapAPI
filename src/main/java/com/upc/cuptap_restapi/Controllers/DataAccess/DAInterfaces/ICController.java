package com.upc.cuptap_restapi.Controllers.DataAccess.DAInterfaces;

import com.upc.cuptap_restapi.Models.Interfaces.CreateEntity;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICController<E extends CreateEntity> extends Controller {
    ResponseEntity<Response<E>> Save(@RequestBody E item);

}
