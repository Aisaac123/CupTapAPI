package com.upc.cuptap_restapi.Controllers.Providers.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICController<E extends CreateEntity> extends Controller {
    ResponseEntity<Response<E>> Save(@RequestBody E item);

}
