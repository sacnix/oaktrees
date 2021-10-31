package co.com.oaktrees.repository;

import co.com.oaktrees.entity.Categoria;
import co.com.oaktrees.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNombre(String nombre);

    Optional<Categoria> findById(String id);

    boolean existsById(String id);

    boolean existsByNombre(String nombre);

    void deleteById(String id);
}
