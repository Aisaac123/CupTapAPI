package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Services.Service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/CupTapAPI/v1/Pedidos")
@Tag(name = "Pedidos", description = "Controlador del modulo de pedidos")

public class PedidoController implements CRUDControllerInstance<Pedido, Long> {

    final
    PedidoService serv;

    public PedidoController(PedidoService serv) {
        this.serv = serv;
    }

    @Override
    public CController<Pedido, Long> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Pedido, Long> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Pedido, Long> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Pedido, Long> Modify() {
        return new UController<>(serv.Modify());
    }


    @GetMapping("")
    @Operation(summary = "Consulta todos los pedidos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los pedidos registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<List<Pedido>>> GetAll() {
        return Read().GetAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta de pedidos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos de los pedidos correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Pedido>> GetById(@PathVariable Long id) {
        return Read().GetById(id);
    }

    @GetMapping("/{page_index}/{limit}")
    @Operation(summary = "Consulta de pedidos (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los pedidos solicitados"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Page<Pedido>>> GetPageable(@PathVariable int page_index, @PathVariable int limit) {
        return Read().GetPageable(page_index, limit);
    }

    @PostMapping("")
    @Operation(summary = "Permite registrar pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente el pedido"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Pedido>> Save(@RequestBody Pedido entity) {
        return Persist().Save(entity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Permite actualizar los datos del pedido por medio de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos del pedido"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Map<String, Pedido>>> Update(@PathVariable Long id, @RequestBody Pedido new_entity) {
        return Modify().Update(new_entity, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar el pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente el detalle del pedido"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Pedido>> Delete(@PathVariable Long id) {
        return Remove().Delete(id);
    }


}
