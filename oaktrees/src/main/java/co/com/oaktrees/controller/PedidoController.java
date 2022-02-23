package co.com.oaktrees.controller;

import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.dto.PedidoDTO;
import co.com.oaktrees.entity.Pedido;
import co.com.oaktrees.service.PedidoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "https://oak-trees-angular.herokuapp.com")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Pedido>> list() {
        List<Pedido> list = pedidoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/historico/{idUsuario}")
    public ResponseEntity<List<Pedido>> listByCorreo(@PathVariable("idUsuario") String idUsuario) {
        List<Pedido> list = pedidoService.listByCorreo(idUsuario);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{idPedido}")
    public ResponseEntity<Pedido> getById(@PathVariable("idPedido") int idPedido) {
        if (!pedidoService.existsById(idPedido))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Pedido pedido = pedidoService.getOne(idPedido).get();
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @GetMapping("/detailname/{idUsuario}")
    public ResponseEntity<Pedido> getByIdUsuario(@PathVariable("idUsuario") String idUsuario) {
        if (!pedidoService.existsByIdUsuario(idUsuario))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Pedido pedido = pedidoService.getByIdUsuario(idUsuario).get();
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PedidoDTO pedidoDTO){
        if (StringUtils.isBlank(pedidoDTO.getIdUsuario()))
            return new ResponseEntity<>(new Mensaje("El ID del usuario es obligatorio"), HttpStatus.BAD_REQUEST);
        Pedido pedido = new Pedido(pedidoDTO.getFecha(), pedidoDTO.getValorTotal(), pedidoDTO.getTipoEntrega(),
                pedidoDTO.getTelefono(), pedidoDTO.getDireccion(), pedidoDTO.getIdEstado(), pedidoDTO.getIdUsuario(),
                pedidoDTO.getComentario());
        pedido.setFecha(new Date());
        pedido.setProductos(pedidoDTO.getProductos());
        pedidoService.save(pedido);
        return new ResponseEntity<>(new Mensaje("El pedido ha sido creado correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{idPedido}")
    public ResponseEntity<?> update(@PathVariable("idPedido") int idPedido, @RequestBody PedidoDTO pedidoDTO) {
        if (!pedidoService.existsById(idPedido))
            return new ResponseEntity(new Mensaje("El pedido no existe"), HttpStatus.NOT_FOUND);
        Pedido pedido = pedidoService.getOne(idPedido).get();
        pedido.setIdEstado(pedidoDTO.getIdEstado());
        pedido.setComentario(pedidoDTO.getComentario());
        pedidoService.save(pedido);
        return new ResponseEntity<>(new Mensaje("El pedido ha sido actualizado correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') " + "|| hasRole('VENDEDOR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!pedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        pedidoService.delete(id);
        return new ResponseEntity<>(new Mensaje("El carrito ha sido eliminada correctamente"), HttpStatus.OK);
    }
}
