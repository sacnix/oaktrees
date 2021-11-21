package co.com.oaktrees.controller;

import co.com.oaktrees.dto.*;
import co.com.oaktrees.entity.Persona;
import co.com.oaktrees.entity.Rol;
import co.com.oaktrees.enums.RolNombre;
import co.com.oaktrees.security.jwt.JwtProvider;
import co.com.oaktrees.service.PersonaService;
import co.com.oaktrees.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PersonaService personaService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/auth/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevaPersona nuevaPersona, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if (personaService.existsByCorreo(nuevaPersona.getCorreo()))
            return new ResponseEntity(new Mensaje("El correo electrónico ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
        Persona persona =
                new Persona(nuevaPersona.getNombre(), nuevaPersona.getTelefono(),
                        nuevaPersona.getCorreo(), passwordEncoder.encode(nuevaPersona.getClave()));
        Set<Rol> roles = new HashSet<>();
        if (nuevaPersona.getRol().equals("1")) {
            roles.add(rolService.getByNombre(RolNombre.ROL_ADMIN).get());
            persona.setRoles(roles);
        }
        if (nuevaPersona.getRol().equals("2")) {
            roles.add(rolService.getByNombre(RolNombre.ROL_USER).get());
            persona.setRoles(roles);
        }
        if (nuevaPersona.getRol().equals("3")) {
            roles.add(rolService.getByNombre(RolNombre.ROL_VENDEDOR).get());
            persona.setRoles(roles);
        }
        persona.setRoles(roles);
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Usuario creado exitosamente"), HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Los datos son incorrectos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getCorreo(),
                        loginUsuario.getClave()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generarToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = personaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{correo}")
    public ResponseEntity<Persona> getById(@PathVariable("correo") String correo) {
        if (!personaService.existsByCorreo(correo))
            return new ResponseEntity(new Mensaje("Usuario no existe"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getOne(correo).get();
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PutMapping("/editar-persona/{correo}")
    public ResponseEntity<?> actualizar(@PathVariable("correo") String correo,
                                        @RequestBody PersonaEditada personaEditada) {
        if (!personaService.existsByCorreo(correo))
            return new ResponseEntity(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getOne(correo).get();
        persona.setNombre(personaEditada.getNombre());
        persona.setTelefono(personaEditada.getTelefono());
        persona.setCorreo(personaEditada.getCorreo());
        Set<Rol> roles = new HashSet<>();
        if (personaEditada.getRol() != null) {
            if (personaEditada.getRol().equals("1")) {
                roles.add(rolService.getByNombre(RolNombre.ROL_ADMIN).get());
                persona.setRoles(roles);
            } else if (personaEditada.getRol().equals("2")) {
                roles.add(rolService.getByNombre(RolNombre.ROL_USER).get());
                persona.setRoles(roles);
            } else if (personaEditada.getRol().equals("3")) {
                roles.add(rolService.getByNombre(RolNombre.ROL_VENDEDOR).get());
                persona.setRoles(roles);
            }
        }
        personaService.save(persona);
        return new ResponseEntity<>(new Mensaje("La información ha sido actualizada correctamente"), HttpStatus.OK);
    }

    @PutMapping("/cambiar-clave/{correo}")
    public ResponseEntity<?> cambiarClave(@PathVariable("correo") String correo, @RequestBody PersonaEditada personaEditada) {
        if (!personaService.existsByCorreo(correo))
            return new ResponseEntity(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getOne(correo).get();
        persona.setClave(passwordEncoder.encode(personaEditada.getClave()));
        personaService.save(persona);
        return new ResponseEntity<>(new Mensaje("La información ha sido actualizada correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{correo}")
    public ResponseEntity<?> delete(@PathVariable("correo") String correo) {
        if (!personaService.existsByCorreo(correo))
            return new ResponseEntity(new Mensaje("No existe el usuario"), HttpStatus.NOT_FOUND);
        personaService.delete(correo);
        return new ResponseEntity<>(new Mensaje("La cuenta ha sido eliminada correctamente"), HttpStatus.OK);
    }


    /**


     @GetMapping("/detailname/{nombre}") public ResponseEntity<Persona> getByNombre(@PathVariable("nombre") String nombre){
     if(!personaService.existsByNombre(nombre))
     return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
     Persona persona = personaService.getByNombre(nombre).get();
     return new ResponseEntity<>(persona, HttpStatus.OK);
     }

     @PostMapping("/create") public ResponseEntity<?> create(@RequestBody PersonaDTO personaDTO){
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

     @PutMapping("/update/{id}") public ResponseEntity<?> update(@PathVariable("id")String id, @RequestBody PersonaDTO personaDTO){
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
     **/
}
