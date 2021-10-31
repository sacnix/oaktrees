package co.com.oaktrees.service;

import co.com.oaktrees.entity.Categoria;
import co.com.oaktrees.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> list(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getOne(String id){
        return categoriaRepository.findById(id);
    }

    public Optional<Categoria> getByNombre(String nombre){
        return categoriaRepository.findByNombre(nombre);
    }

    public void save(Categoria persona){
        categoriaRepository.save(persona);
    }

    public void delete(String id){
        categoriaRepository.deleteById(id);
    }

    public boolean existsById(String id){
        return categoriaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return categoriaRepository.existsByNombre(nombre);
    }
}
