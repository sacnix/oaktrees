package co.com.oaktrees.controller;

import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.dto.ProductoDTO;
import co.com.oaktrees.entity.Producto;
import co.com.oaktrees.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/list")
    public ResponseEntity<List<Producto>> list() {
        List<Producto> list = productoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") String id) {
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre) {
        if (!productoService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoDTO productoDTO) {
        if (productoService.existsById(productoDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese producto ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(productoDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (productoService.existsByNombre(productoDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(productoDTO.getColor()))
            return new ResponseEntity<>(new Mensaje("El color es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(productoDTO.getImagen()))
            return new ResponseEntity<>(new Mensaje("La imagen es obligatoria"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(productoDTO.getIdCategoria()))
            return new ResponseEntity<>(new Mensaje("La categoría es obligatoria"), HttpStatus.BAD_REQUEST);
        Producto producto = new Producto(productoDTO.getIdProducto(),productoDTO.getDescripcion(),
                productoDTO.getPrecio(),productoDTO.getColor(),productoDTO.getImagen(), productoDTO.getEstado(),
                productoDTO.getVisibilidad(), productoDTO.getCantidad(), productoDTO.getIdCategoria(),
                productoDTO.getIdCarrito());
        productoService.save(producto);
        return new ResponseEntity<>(new Mensaje("El producto ha sido creado correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody ProductoDTO productoDTO) {
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("El producto no existe"), HttpStatus.NOT_FOUND);
        if (productoService.existsByNombre(productoDTO.getNombre()) && productoService.getByNombre(productoDTO.getNombre()).get().getIdProducto() != id)
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(productoDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(productoDTO.getColor()))
            return new ResponseEntity<>(new Mensaje("El color es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(productoDTO.getImagen()))
            return new ResponseEntity<>(new Mensaje("La imagen es obligatoria"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(productoDTO.getIdCategoria()))
            return new ResponseEntity<>(new Mensaje("La categoría es obligatoria"), HttpStatus.BAD_REQUEST);
        Producto producto = productoService.getOne(id).get();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setColor(productoDTO.getColor());
        producto.setImagen(productoDTO.getImagen());
        producto.setEstado(productoDTO.getEstado());
        producto.setVisibilidad(productoDTO.getVisibilidad());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setIdCategoria(productoDTO.getIdCategoria());
        producto.setIdCarrito(productoDTO.getIdCarrito());
        productoService.save(producto);
        return new ResponseEntity<>(new Mensaje("El producto ha sido actualizado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity<>(new Mensaje("El producto ha sido eliminado correctamente"), HttpStatus.OK);
    }
}
