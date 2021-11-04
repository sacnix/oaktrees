package co.com.oaktrees.service;

import co.com.oaktrees.entity.Rol;
import co.com.oaktrees.enums.RolNombre;
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

    public Optional<Rol> getByNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }

}
