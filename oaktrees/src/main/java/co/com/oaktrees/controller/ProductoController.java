package co.com.oaktrees.controller;

import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.entity.Producto;
import co.com.oaktrees.service.CloudinaryService;
import co.com.oaktrees.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/list")
    public ResponseEntity<List<Producto>> list(){
        List<Producto> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestPart("multipartFile") MultipartFile multipartFile,
                                    @RequestPart("nombre") String nombre,
                                    @RequestPart("descripcion") String descripcion,
                                    @RequestPart("precio") String precio, @RequestPart("color") String color,
                                    @RequestPart("estado") String estado,
                                    @RequestPart("visibilidad") String visibilidad,
                                    @RequestPart("cantidad") String cantidad,
                                    @RequestPart("categoria") String categoria
                                    )throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Mensaje("La imagen no es válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Producto imagen =
                new Producto(nombre, descripcion, Float.parseFloat(precio),
                        color, (String)result.get("url"), (String)result.get("public_id"), Integer.parseInt(estado),
                        Integer.parseInt(visibilidad),
                        Integer.parseInt(cantidad), categoria);
        productoService.save(imagen);
        return new ResponseEntity(new Mensaje("El producto ha sido creado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idProducto}")
    public ResponseEntity<?> delete(@PathVariable("idProducto") int id)throws IOException {
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        Map result = cloudinaryService.delete(producto.getImagenId());
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("El producto ha sido eliminado"), HttpStatus.OK);
    }

    @PutMapping("/update/{idProducto}")
    public ResponseEntity<Producto> getById(@PathVariable("idProducto") int id,
                                            @RequestPart("multipartFile") MultipartFile multipartFile,
                                            @RequestPart("nombre") String nombre,
                                            @RequestPart("descripcion") String descripcion,
                                            @RequestPart("precio") String precio, @RequestPart("color") String color,
                                            @RequestPart("estado") String estado,
                                            @RequestPart("visibilidad") String visibilidad,
                                            @RequestPart("cantidad") String cantidad,
                                            @RequestPart("categoria") String categoria
    ) throws IOException {
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        String imagenUrlResult;
        String imagenIdResult;
        if(multipartFile.getResource().getFilename().equals(producto.getImagenId())){
            imagenUrlResult = producto.getImagenUrl();
            imagenIdResult = producto.getImagenId();
        }else{
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if(bi == null){
                return new ResponseEntity(new Mensaje("La imagen no es válida"), HttpStatus.BAD_REQUEST);
            }
            Map result = cloudinaryService.upload(multipartFile);
            cloudinaryService.delete(producto.getImagenId());
            imagenUrlResult = (String)result.get("url");
            imagenIdResult = (String)result.get("public_id");
        }
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(Float.parseFloat(precio));
        producto.setColor(color);
        producto.setEstado(Integer.parseInt(estado));
        producto.setVisibilidad(Integer.parseInt(visibilidad));
        producto.setCantidad(Integer.parseInt(cantidad));
        producto.setImagenUrl(imagenUrlResult);
        producto.setImagenId(imagenIdResult);
        producto.setCategoria(categoria);
        productoService.save(producto);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/detail/{idProducto}")
    public ResponseEntity<Producto> getById(@PathVariable("idProducto") int id) {
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    /**


   @GetMapping("/list")
    public ResponseEntity<List<Producto>> list() {
        List<Producto> list = productoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id) {
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


    @PreAuthorize("hasRole('ADMIN') " + "|| hasRole('VENDEDOR')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoDTO productoDTO) {
        if (productoService.existsByNombre(productoDTO.getNombre()))
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
        Producto producto = new Producto(productoDTO.getNombre(),productoDTO.getDescripcion(),
                productoDTO.getPrecio(),productoDTO.getColor(),productoDTO.getImagen(), productoDTO.getEstado(),
                productoDTO.getVisibilidad(), productoDTO.getCantidad(), productoDTO.getIdCategoria(),
                productoDTO.getIdCarrito());
        productoService.save(producto);
        return new ResponseEntity<>(new Mensaje("El producto ha sido creado correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') " + "|| hasRole('VENDEDOR')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProductoDTO productoDTO) {
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

    @PreAuthorize("hasRole('ADMIN') " + "|| hasRole('VENDEDOR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity<>(new Mensaje("El producto ha sido eliminado correctamente"), HttpStatus.OK);
    }**/
}
