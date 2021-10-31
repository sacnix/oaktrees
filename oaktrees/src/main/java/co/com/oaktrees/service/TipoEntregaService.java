package co.com.oaktrees.service;

import co.com.oaktrees.entity.TipoEntrega;
import co.com.oaktrees.repository.TipoEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipoEntregaService {

    @Autowired
    TipoEntregaRepository tipoEntregaRepository;

    public List<TipoEntrega> list(){
        return tipoEntregaRepository.findAll();
    }

    public Optional<TipoEntrega> getOne(int id){
        return tipoEntregaRepository.findById(id);
    }

    public Optional<TipoEntrega> getByNombre(String nombre){
        return tipoEntregaRepository.findByNombre(nombre);
    }

    public void save(TipoEntrega tipoEntrega){
        tipoEntregaRepository.save(tipoEntrega);
    }

    public void delete(int id){
        tipoEntregaRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return tipoEntregaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return tipoEntregaRepository.existsByNombre(nombre);
    }
}
