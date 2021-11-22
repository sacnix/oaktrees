package co.com.oaktrees.controller;


import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.entity.Producto;
import co.com.oaktrees.service.CloudinaryService;
import co.com.oaktrees.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/auth/catalogo")
@CrossOrigin(origins = "http://localhost:4200")
public class CatalogoController {

    @Autowired
    ProductoService productoService;

    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> listar(){
        List<Producto> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{idProducto}")
    public ResponseEntity<Producto> getById(@PathVariable("idProducto") int id) {
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }
}
