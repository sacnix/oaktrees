package co.com.oaktrees.service;

import co.com.oaktrees.entity.Persona;
import co.com.oaktrees.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> list(){
        return personaRepository.findAll();
    }

    public Optional<Persona> getOne(String correo){
        return personaRepository.findByCorreo(correo);
    }

    public Optional<Persona> getByNombre(String nombre){
        return personaRepository.findByNombre(nombre);
    }

    public void save(Persona persona){
        personaRepository.save(persona);
    }

    public void delete(String correo){
        personaRepository.deleteByCorreo(correo);
    }

    public boolean existsByCorreo(String correo){
        return personaRepository.existsByCorreo(correo);
    }

    public boolean existsByNombre(String nombre){
        return personaRepository.existsByNombre(nombre);
    }
}
