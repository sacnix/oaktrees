package co.com.oaktrees.controller;

import co.com.oaktrees.dto.EstadoPedidoDTO;
import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.entity.EstadoPedido;
import co.com.oaktrees.service.EstadoPedidoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estado-pedido")
@CrossOrigin(origins = "https://oak-trees-angular.herokuapp.com")
public class EstadoPedidoController {

    @Autowired
    EstadoPedidoService estadoPedidoService;

    @GetMapping("/lista")
    public ResponseEntity<List<EstadoPedido>> list(){
        List<EstadoPedido> list = estadoPedidoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<EstadoPedido> getById(@PathVariable("id") int id){
        if(!estadoPedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        EstadoPedido estadoPedido = estadoPedidoService.getOne(id).get();
        return new ResponseEntity<>(estadoPedido, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<EstadoPedido> getByNombre(@PathVariable("nombre") String nombre){
        if(!estadoPedidoService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        EstadoPedido estadoPedido = estadoPedidoService.getByNombre(nombre).get();
        return new ResponseEntity<>(estadoPedido, HttpStatus.OK);
    }
/**
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EstadoPedidoDTO estadoPedidoDTO){
        if(StringUtils.isBlank(estadoPedidoDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(estadoPedidoService.existsByNombre(estadoPedidoDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        EstadoPedido estadoPedido = new EstadoPedido(estadoPedidoDTO.getNombre());
        estadoPedidoService.save(estadoPedido);
        return new ResponseEntity<>(new Mensaje("Estado pedido creado correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody EstadoPedidoDTO estadoPedidoDTO){
        if(!estadoPedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        if(estadoPedidoService.existsByNombre(estadoPedidoDTO.getNombre()) && estadoPedidoService.getByNombre(estadoPedidoDTO.getNombre()).get().getIdEstado() != id)
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(estadoPedidoDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        EstadoPedido estadoPedido = estadoPedidoService.getOne(id).get();
        estadoPedido.setNombre(estadoPedidoDTO.getNombre());
        estadoPedidoService.save(estadoPedido);
        return new ResponseEntity<>(new Mensaje("Estado pedido actualizado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!estadoPedidoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        estadoPedidoService.delete(id);
        return new ResponseEntity<>(new Mensaje("Rol eliminado correctamente"), HttpStatus.OK);
    }**/
}
