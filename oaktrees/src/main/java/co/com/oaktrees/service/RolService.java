package co.com.oaktrees.service;

import co.com.oaktrees.entity.Rol;
import co.com.oaktrees.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public List<Rol> list(){
        return rolRepository.findAll();
    }

    public Optional<Rol> getOne(int id){
        return rolRepository.findById(id);
    }

    public Optional<Rol> getByNombre(String nombre){
        return rolRepository.findByNombre(nombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }

    public void delete(int id){
        rolRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return rolRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return rolRepository.existsByNombre(nombre);
    }
}
