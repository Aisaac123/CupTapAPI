package com.upc.cuptap_restapi.Controllers.Controller;


import com.upc.cuptap_restapi.Controllers.Providers.Providers.CController;
import com.upc.cuptap_restapi.Controllers.Providers.Providers.DController;
import com.upc.cuptap_restapi.Controllers.Providers.Providers.RController;
import com.upc.cuptap_restapi.Controllers.Providers.Providers.UController;
import com.upc.cuptap_restapi.Controllers.Providers.ProvidersInstances.CRUDControllerInstance;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.ProductoRequest;
import com.upc.cuptap_restapi.Models.Entities.Producto;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Services.Logic.ProductoService;
import com.upc.cuptap_restapi.Services.Middlewares.ReconstructRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/v1/Productos")
@Tag(name = "Productos", description = "Controlador del modulo de productos")

public class ProductoController implements CRUDControllerInstance<Producto, String> {

    final
    ProductoService serv;
    final
    ReconstructRequest reconstruct;

    public ProductoController(ProductoService serv, ReconstructRequest reconstruct) {
        this.serv = serv;
        this.reconstruct = reconstruct;
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


    @GetMapping("/{nombre}")
    @Operation(summary = "Consulta productos por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos del producto correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el producto por nombre", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Producto>> GetById(@PathVariable String nombre, @RequestParam(value = "lazy", required = false) boolean isLazy) {
        return Read().GetById(nombre, isLazy);
    }

    @GetMapping("")
    @Operation(summary = "Consulta de productos (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los productos solicitados"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response> GetAll(@RequestParam(value = "index", defaultValue = "-1", required = false) int page_index,
                                           @RequestParam(value = "limit", defaultValue = "-1", required = false) int limit,
                                           @RequestParam(value = "dateLimit", required = false) LocalDate fecha,
                                           @RequestParam(value = "lazy", required = false) boolean isLazy) {
        return Read().GetAll(page_index, limit, fecha, isLazy);
    }

    @PostMapping("")
    @Operation(summary = "Permite registrar productos", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ProductoRequest.class),
                    examples = {@ExampleObject(value = "{\"nombre\": \"string\", \"descripcion\": \"string\", \"precio\": 0.1, \"stock\": 100, \"ventaActiva\": true, \"promocion\": {\"id\": 1}, \"imagen\": [\"dato de tipo ByteArray\"]}")})))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente al producto"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Producto>> Save(@RequestBody ProductoRequest producto) {
        return Persist().Save(producto.toEntity());
    }

    @PutMapping("/{nombre}")
    @Operation(summary = "Permite actualizar los datos del producto por medio de su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos del producto"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el producto por nombre", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Map<String, Producto>>> Update(@PathVariable String nombre, @RequestBody ProductoRequest new_producto) {
        return Modify().Update(new_producto.toEntity(), nombre);
    }

    @DeleteMapping("/{nombre}")
    @Operation(summary = "Permite eliminar productos por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente al producto"),
            @ApiResponse(responseCode = "404", description = "No se encontro el producto por nombre", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Producto>> Delete(@PathVariable String nombre) {
        return Remove().Delete(nombre);
    }

    // TODO Controladores especificos

    @PatchMapping("/{nombre}/cantidad/{cantidad}")
    @Operation(summary = "Permite actualizar los la cantidad en stock del producto por medio de su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizó correctamete la cantidad en stock del producto "),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))})
    })

    public ResponseEntity<Response> UpdateStock(@PathVariable String nombre,
                                                @PathVariable int cantidad,
                                                @RequestParam(required = false, name = "operation", defaultValue = "update") String operation) {
        var res = serv.UpdateStock(nombre, cantidad, operation);
        var code = res.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(res, code);
    }
    @PatchMapping("/{nombre}/cantidad")
    @Operation(summary = "Permite actualizar los la cantidad en stock del varios productos a la vez",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Map.class),
                    examples = {@ExampleObject(value = "{\"example_ProductoNombre1\": 100, \"example_ProductoNombre2\": 50}")})))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizó correctamete la cantidad en stock de todos los productos "),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))})
    })
    public ResponseEntity<Response> UpdateStock(Map<String, Integer> requests) {
        var res = serv.UpdateStock(requests);
        var code = res.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(res, code);
    }

}
