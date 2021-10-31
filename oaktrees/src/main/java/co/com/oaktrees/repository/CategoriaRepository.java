package co.com.oaktrees.repository;

import co.com.oaktrees.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNombre(String nombre);

    Optional<Categoria> findById(int id);

    boolean existsById(int id);

    boolean existsByNombre(String nombre);

    void deleteById(int id);
}
