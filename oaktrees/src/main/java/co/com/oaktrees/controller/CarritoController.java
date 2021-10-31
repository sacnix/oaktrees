package co.com.oaktrees.controller;

import co.com.oaktrees.dto.CarritoDTO;
import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.entity.Carrito;
import co.com.oaktrees.service.CarritoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "http://localhost:4200")
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Carrito>> list(){
        List<Carrito> list = carritoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Carrito> getById(@PathVariable("id") String id){
        if(!carritoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Carrito carrito = carritoService.getOne(id).get();
        return new ResponseEntity<>(carrito, HttpStatus.OK);
    }

    @GetMapping("/detailname/{idUsuario}")
    public ResponseEntity<Carrito> getByIdUsuario(@PathVariable("idUsuario") String idUsuario){
        if(!carritoService.existsByIdUsuario(idUsuario))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Carrito carrito = carritoService.getByIdUsuario(idUsuario).get();
        return new ResponseEntity<>(carrito, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CarritoDTO carritoDTO){
        if(StringUtils.isBlank(carritoDTO.getIdUsuario()))
            return new ResponseEntity<>(new Mensaje("El ID del usuario es obligatorio"), HttpStatus.BAD_REQUEST);
         Carrito carrito = new Carrito(carritoDTO.getSubTotal(), carritoDTO.getTotal(), carritoDTO.getIdUsuario());
        carritoService.save(carrito);
        return new ResponseEntity<>(new Mensaje("El carrito ha sido creado correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")String id, @RequestBody CarritoDTO carritoDTO){
        if(!carritoService.existsById(id))
            return new ResponseEntity(new Mensaje("El carrito no existe"), HttpStatus.NOT_FOUND);
        if(carritoService.existsByIdUsuario(carritoDTO.getIdUsuario()) && carritoService.getOne(carritoDTO.getIdCarrito()).get().getIdCarrito() != id)
            return new ResponseEntity<>(new Mensaje("Ese usuario ya posee un carrito"),
                    HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(carritoDTO.getIdUsuario()))
            return new ResponseEntity<>(new Mensaje("El ID del usuario es obligatorio"), HttpStatus.BAD_REQUEST);
        Carrito carrito = carritoService.getOne(id).get();
        carrito.setSubTotal(carritoDTO.getSubTotal());
        carrito.setTotal(carritoDTO.getTotal());
        carrito.setIdUsuario(carritoDTO.getIdUsuario());
        carritoService.save(carrito);
        return new ResponseEntity<>(new Mensaje("El carrito ha sido actualizado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")String id){
        if(!carritoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        carritoService.delete(id);
        return new ResponseEntity<>(new Mensaje("El carrito ha sido eliminada correctamente"), HttpStatus.OK);
    }
}
