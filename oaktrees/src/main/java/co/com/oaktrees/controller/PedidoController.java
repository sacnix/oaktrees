package co.com.oaktrees.controller;

import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.dto.PedidoDTO;
import co.com.oaktrees.entity.Pedido;
import co.com.oaktrees.service.PedidoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Pedido>> list() {
        List<Pedido> list = pedidoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable("id") String id) {
        if (!pedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Pedido pedido = pedidoService.getOne(id).get();
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
    public ResponseEntity<?> create(@RequestBody PedidoDTO pedidoDTO) {
        if (StringUtils.isBlank(pedidoDTO.getIdUsuario()))
            return new ResponseEntity<>(new Mensaje("El ID del usuario es obligatorio"), HttpStatus.BAD_REQUEST);
        Pedido pedido = new Pedido(pedidoDTO.getFecha(), pedidoDTO.getValorTotal(), pedidoDTO.getIdTipoEntrega(),
                pedidoDTO.getIdEstado(), pedidoDTO.getIdUsuario(), pedidoDTO.getIdCarrito());
        pedidoService.save(pedido);
        return new ResponseEntity<>(new Mensaje("El pedido ha sido creado correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody PedidoDTO pedidoDTO) {
        if (!pedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("El carrito no existe"), HttpStatus.NOT_FOUND);
        if (pedidoService.existsByIdUsuario(pedidoDTO.getIdUsuario()) && pedidoService.getOne(pedidoDTO.getIdCarrito()).get().getIdCarrito() != id)
            return new ResponseEntity<>(new Mensaje("Ese usuario ya posee un carrito"),
                    HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(pedidoDTO.getIdUsuario()))
            return new ResponseEntity<>(new Mensaje("El ID del usuario es obligatorio"), HttpStatus.BAD_REQUEST);
        Pedido pedido = pedidoService.getOne(id).get();
        pedido.setFecha(pedidoDTO.getFecha());
        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setIdTipoEntrega(pedidoDTO.getIdTipoEntrega());
        pedido.setIdEstado(pedidoDTO.getIdEstado());
        pedido.setIdUsuario(pedidoDTO.getIdUsuario());
        pedido.setIdCarrito(pedidoDTO.getIdCarrito());
        pedidoService.save(pedido);
        return new ResponseEntity<>(new Mensaje("El pedido ha sido actualizado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        if (!pedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        pedidoService.delete(id);
        return new ResponseEntity<>(new Mensaje("El carrito ha sido eliminada correctamente"), HttpStatus.OK);
    }
}
