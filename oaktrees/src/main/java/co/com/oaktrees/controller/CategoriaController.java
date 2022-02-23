package co.com.oaktrees.controller;

import co.com.oaktrees.dto.CategoriaDTO;
import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.entity.Categoria;
import co.com.oaktrees.service.CategoriaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "https://oak-trees-angular.herokuapp.com")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Categoria>> list(){
        List<Categoria> list = categoriaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable("id") int id){
        if(!categoriaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Categoria categoria = categoriaService.getOne(id).get();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Categoria> getByNombre(@PathVariable("nombre") String nombre){
        if(!categoriaService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Categoria categoria = categoriaService.getByNombre(nombre).get();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CategoriaDTO categoriaDTO){
        if(StringUtils.isBlank(categoriaDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(categoriaService.existsByNombre(categoriaDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Categoria categoria = new Categoria(categoriaDTO.getNombre());
        categoriaService.save(categoria);
        return new ResponseEntity<>(new Mensaje("Categoría creada correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody CategoriaDTO categoriaDTO){
        if(!categoriaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        if(categoriaService.existsByNombre(categoriaDTO.getNombre()) && categoriaService.getByNombre(categoriaDTO.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(categoriaDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        Categoria categoria = categoriaService.getOne(id).get();
        categoria.setNombre(categoriaDTO.getNombre());
        categoriaService.save(categoria);
        return new ResponseEntity<>(new Mensaje("Categoría actualizada correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!categoriaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        categoriaService.delete(id);
        return new ResponseEntity<>(new Mensaje("Categoría eliminada correctamente"), HttpStatus.OK);
    }
}
