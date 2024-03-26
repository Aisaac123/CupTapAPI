package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.DataAccess.DACInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.CController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.DController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.RController;
import com.upc.cuptap_restapi.Controllers.DataAccess.DAControllers.UController;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Utilities.Response;
import com.upc.cuptap_restapi.Services.Service.ProductoService;
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
@RequestMapping("/CupTapAPI/v1/Productos")
@Tag(name = "Productos", description = "Controlador del modulo de productos")

public class ProductoController implements CRUDControllerInstance<Producto, String> {

    final
    ProductoService serv;

    public ProductoController(ProductoService serv) {
        this.serv = serv;
    }

    @Override
    public CController<Producto, String> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public DController<Producto, String> Remove() {
        return new DController<>(serv.Remove());
    }

    @Override
    public RController<Producto, String> Read() {
        return new RController<>(serv.Read());
    }

    @Override
    public UController<Producto, String> Modify() {
        return new UController<>(serv.Modify());
    }


    @GetMapping("")
    @Operation(summary = "Consulta todos los pedidos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos de todos los pedidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<List<Producto>>> GetAll() {
        return Read().GetAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta pedidos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos del pedido correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Producto>> GetById(@PathVariable String id) {
        return Read().GetById(id);
    }

    @GetMapping("/{page_index}/{limit}")
    @Operation(summary = "Consulta de pedidos (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los pedidos solicitados"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Page<Producto>>> GetPageable(@PathVariable int page_index, @PathVariable int limit) {
        return Read().GetPageable(page_index, limit);
    }

    @PostMapping("")
    @Operation(summary = "Permite registrar pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente al pedido"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Producto>> Save(@RequestBody Producto combo) {
        return Persist().Save(combo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Permite actualizar los datos del pedido por medio de su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos del pedido"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Map<String, Producto>>> Update(@PathVariable String id, @RequestBody Producto new_combo) {
        return Modify().Update(new_combo, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite eliminar pedidos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente al pedido"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por id", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Producto>> Delete(@PathVariable String id) {
        return Remove().Delete(id);
    }


}
