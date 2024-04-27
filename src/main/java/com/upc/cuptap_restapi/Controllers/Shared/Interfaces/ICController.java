package com.upc.cuptap_restapi.Controllers.Shared.Interfaces;

import com.upc.cuptap_restapi.Models.Interfaces.Entities.CreateEntity;
import com.upc.cuptap_restapi.Models.Utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICController<E extends CreateEntity> extends Controller {
    default ResponseEntity<Response<E>> Save(@RequestBody E item){
        return Save(item, false);
    }

    ResponseEntity<Response<E>> Save(@RequestBody E item, boolean transmit);

}
