package co.com.oaktrees.service;

import co.com.oaktrees.entity.Carrito;
import co.com.oaktrees.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarritoService {

    @Autowired
    CarritoRepository carritoRepository ;

    public List<Carrito> list(){
        return carritoRepository.findAll();
    }

    public Optional<Carrito> getOne(String id){
        return carritoRepository.findById(id);
    }

    public Optional<Carrito> getByIdUsuario(String idUsuario){
        return carritoRepository.findByIdUsuario(idUsuario);
    }

    public void save(Carrito persona){
        carritoRepository.save(persona);
    }

    public void delete(String id){
        carritoRepository.deleteById(id);
    }

    public boolean existsById(String id){
        return carritoRepository.existsById(id);
    }

    public boolean existsByIdUsuario(String idUsuario){
        return carritoRepository.existsByIdUsuario(idUsuario);
    }
}
