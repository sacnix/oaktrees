package co.com.oaktrees.controller;

import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.dto.RolDTO;
import co.com.oaktrees.entity.Rol;
import co.com.oaktrees.service.RolService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = "http://localhost:4200")
public class RolController {

    @Autowired
    RolService rolService;

    @GetMapping("/lista")
    public ResponseEntity<List<Rol>> list(){
        List<Rol> list = rolService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Rol> getById(@PathVariable("id") int id){
        if(!rolService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Rol rol = rolService.getOne(id).get();
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Rol> getByNombre(@PathVariable("nombre") String nombre){
        if(!rolService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Rol rol = rolService.getByNombre(nombre).get();
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RolDTO rolDTO){
        if(StringUtils.isBlank(rolDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(rolService.existsByNombre(rolDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Rol rol = new Rol(rolDTO.getNombre());
        rolService.save(rol);
        return new ResponseEntity<>(new Mensaje("Rol creado correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody RolDTO rolDTO){
        if(!rolService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        if(rolService.existsByNombre(rolDTO.getNombre()) && rolService.getByNombre(rolDTO.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(rolDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        Rol rol = rolService.getOne(id).get();
        rol.setNombre(rolDTO.getNombre());
        rolService.save(rol);
        return new ResponseEntity<>(new Mensaje("Rol actualizado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!rolService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        rolService.delete(id);
        return new ResponseEntity<>(new Mensaje("Rol eliminado correctamente"), HttpStatus.OK);
    }
}
