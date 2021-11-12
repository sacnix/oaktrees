package co.com.oaktrees.controller;

import co.com.oaktrees.dto.Mensaje;
import co.com.oaktrees.dto.TipoEntregaDTO;
import co.com.oaktrees.entity.TipoEntrega;
import co.com.oaktrees.service.TipoEntregaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-entrega")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoEntregaController {

    @Autowired
    TipoEntregaService tipoEntregaService;

    @GetMapping("/lista")
    public ResponseEntity<List<TipoEntrega>> list(){
        List<TipoEntrega> list = tipoEntregaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<TipoEntrega> getById(@PathVariable("id") int id){
        if(!tipoEntregaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        TipoEntrega tipoEntrega = tipoEntregaService.getOne(id).get();
        return new ResponseEntity<>(tipoEntrega, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<TipoEntrega> getByNombre(@PathVariable("nombre") String nombre){
        if(!tipoEntregaService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        TipoEntrega tipoEntrega = tipoEntregaService.getByNombre(nombre).get();
        return new ResponseEntity<>(tipoEntrega, HttpStatus.OK);
    }
/**
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TipoEntregaDTO tipoEntregaDTO){
        if(StringUtils.isBlank(tipoEntregaDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(tipoEntregaService.existsByNombre(tipoEntregaDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        TipoEntrega tipoEntrega = new TipoEntrega(tipoEntregaDTO.getNombre());
        tipoEntregaService.save(tipoEntrega);
        return new ResponseEntity<>(new Mensaje("Tipo entrega creado correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody TipoEntregaDTO tipoEntregaDTO){
        if(!tipoEntregaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        if(tipoEntregaService.existsByNombre(tipoEntregaDTO.getNombre()) && tipoEntregaService.getByNombre(tipoEntregaDTO.getNombre()).get().getIdTipoEntrega() != id)
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(tipoEntregaDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        TipoEntrega tipoEntrega = tipoEntregaService.getOne(id).get();
        tipoEntrega.setNombre(tipoEntregaDTO.getNombre());
        tipoEntregaService.save(tipoEntrega);
        return new ResponseEntity<>(new Mensaje("Tipo entrega actualizado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!tipoEntregaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        tipoEntregaService.delete(id);
        return new ResponseEntity<>(new Mensaje("Tipo entrega eliminado correctamente"), HttpStatus.OK);
    }**/
}
