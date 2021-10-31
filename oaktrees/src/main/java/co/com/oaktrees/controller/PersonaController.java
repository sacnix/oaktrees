package co.com.oaktrees.controller;

import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.dto.PersonaDTO;
import co.com.oaktrees.entity.Persona;
import co.com.oaktrees.service.PersonaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") String id){
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Persona> getByNombre(@PathVariable("nombre") String nombre){
        if(!personaService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getByNombre(nombre).get();
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PersonaDTO personaDTO){
        if(StringUtils.isBlank(personaDTO.getIdUsuario()))
            return new ResponseEntity<>(new Mensaje("El correo electrónico es obligatorio"), HttpStatus.BAD_REQUEST);
        if(personaService.existsById(personaDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese correo electrónico ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personaDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(personaService.existsByNombre(personaDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personaDTO.getTelefono()))
            return new ResponseEntity<>(new Mensaje("El teléfono es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personaDTO.getClave()))
            return new ResponseEntity<>(new Mensaje("La clave es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personaDTO.getIdUsuario()))
            return new ResponseEntity<>(new Mensaje("El rol es obligatorio"), HttpStatus.BAD_REQUEST);
        Persona persona = new Persona(personaDTO.getIdUsuario(), personaDTO.getNombre(), personaDTO.getTelefono(),
                personaDTO.getClave(), personaDTO.getIdRol());
        personaService.save(persona);
        return new ResponseEntity<>(new Mensaje("El usuario ha sido creado correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")String id, @RequestBody PersonaDTO personaDTO){
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);
        if(personaService.existsByNombre(personaDTO.getNombre()) && personaService.getByNombre(personaDTO.getNombre()).get().getIdUsuario() != id)
            return new ResponseEntity<>(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personaDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personaDTO.getTelefono()))
            return new ResponseEntity<>(new Mensaje("El teléfono es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personaDTO.getClave()))
            return new ResponseEntity<>(new Mensaje("La clave es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personaDTO.getIdUsuario()))
            return new ResponseEntity<>(new Mensaje("El rol es obligatorio"), HttpStatus.BAD_REQUEST);
        Persona persona = personaService.getOne(id).get();
        persona.setNombre(personaDTO.getNombre());
        persona.setTelefono(personaDTO.getTelefono());
        persona.setClave(personaDTO.getClave());
        persona.setIdRol(personaDTO.getIdRol());
        personaService.save(persona);
        return new ResponseEntity<>(new Mensaje("El usuario ha sido actualizado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")String id){
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        personaService.delete(id);
        return new ResponseEntity<>(new Mensaje("La cuenta ha sido eliminada correctamente"), HttpStatus.OK);
    }
}
