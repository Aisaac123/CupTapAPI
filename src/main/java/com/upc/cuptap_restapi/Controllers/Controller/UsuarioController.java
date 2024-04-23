package com.upc.cuptap_restapi.Controllers.Controller;

import com.upc.cuptap_restapi.Controllers.Providers.Providers.CController;
import com.upc.cuptap_restapi.Controllers.Providers.Providers.RController;
import com.upc.cuptap_restapi.Controllers.Providers.ProvidersInstances.CControllerInstance;
import com.upc.cuptap_restapi.Controllers.Providers.ProvidersInstances.RControllerInstance;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.PedidoRequestNoCedula;
import com.upc.cuptap_restapi.Models.DTO.DTORequest.UsuarioRequest;
import com.upc.cuptap_restapi.Models.Entities.Pedido;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Models.Utils.Response;
import com.upc.cuptap_restapi.Models.Utils.ResponseBuilder;
import com.upc.cuptap_restapi.Services.Logic.PedidoService;
import com.upc.cuptap_restapi.Services.Logic.UsuarioService;
import com.upc.cuptap_restapi.Services.Middlewares.ReconstructRequest;
import com.upc.cuptap_restapi.Services.Middlewares.Validations.Requests.PedidoRequestValidations;
import com.upc.cuptap_restapi.Services.Utils.Options.ReadingOptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/v1/Usuarios")
@Tag(name = "Usuarios", description = "Controlador del modulo de usuarios")

public class UsuarioController implements CControllerInstance<Usuario, UUID>, RControllerInstance<Usuario, UUID>  {


    final
    PedidoService pedidoServ;
    private final UsuarioService serv;
    final
    ReconstructRequest reconstruct;


    public UsuarioController(UsuarioService usuarioService, PedidoService pedidoServ, ReconstructRequest reconstruct) {
        serv = usuarioService;
        this.pedidoServ = pedidoServ;
        this.reconstruct = reconstruct;
    }

    @Override
    public CController<Usuario, UUID> Persist() {
        return new CController<>(serv.Persist());
    }

    @Override
    public RController<Usuario, UUID> Read() {
        return new RController<>(serv.Read());
    }

    @GetMapping("")
    @Operation(summary = "Consulta de usuarios (Paginacion)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la pagina con los usuarios solicitados"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response> GetAll(@RequestParam(name = "index", defaultValue = "-1", required = false) int page_index,
                                           @RequestParam(name = "limit", defaultValue = "-1", required = false) int limit,
                                           @RequestParam(name = "dateLimit", required = false) LocalDate fecha,
                                           @RequestParam(name = "lazy", required = false) boolean isLazy) {
        return Read().GetAll(page_index, limit, fecha, isLazy);
    }

    @PostMapping("")
    @Operation(summary = "Permite registrar usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se agregó correctamente al usuario"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Usuario>> Save(@RequestBody UsuarioRequest usuario) {
        return Persist().Save(reconstruct.reconstruct(usuario));
    }


    // Metodos especificos


    @PutMapping("/{cedula}")
    @Operation(summary = "Permite actualizar los datos del UsuarioDTOPedidoRequest por medio de su cedula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizo correctamente los datos del usuario"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta (JSON invalido)", content = {@Content(schema = @Schema(implementation = Response.Doc.BadRequest.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por cedula", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Map<String, Usuario>>> PutByCedula(@PathVariable String cedula, @RequestBody UsuarioRequest new_user, @RequestParam(value = "lazy", required = false) boolean isLazy) {
        try {
            var response = serv.UpdateByCedula(reconstruct.reconstruct(new_user), cedula);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{cedula}")
    @Operation(summary = "Permite eliminar UsuarioDTOPedidoRequest por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó correctamente al usuario"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por cedula", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Usuario>> DeleteByCedula(@PathVariable String cedula) {
        try {
            var response = serv.DeleteByCedula(cedula);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cedula}")
    @Operation(summary = "Consulta usuario por su cedula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los datos del usuario correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por cedula", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Usuario>> GetByCedula(@PathVariable String cedula, @RequestParam(name = "lazy", required = false) boolean isLazy) {
        try {
            var response = serv.GetByCedula(cedula, new ReadingOptions().withLazy(isLazy));
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO Controladores especificos


    @GetMapping("/{cedula}/pedidos")
    @Operation(summary = "Obtiene los pedidos de un usuario en especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra los pedidos realizados del usuario correspondiente"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por cedula", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<List<Pedido>>> GetPedidos(@PathVariable String cedula) {
        try {
            var response = serv.GetPedidos(cedula);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{cedula}/pedidos")
    @Operation(summary = "Crea un pedido para un usuario en especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha creado el pedido para el usuario"),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario por cedula", content = {@Content(schema = @Schema(implementation = Response.Doc.NotFound.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(schema = @Schema(implementation = Response.Doc.InternalServerError.class))})
    })
    public ResponseEntity<Response<Pedido>> AddPedidos(@RequestBody PedidoRequestNoCedula pedidoDTO, @PathVariable String cedula) {
        try {
            var response = serv.AddPedidoToUsuario(pedidoDTO, cedula);
            HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseBuilder.Error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
