package co.com.oaktrees.repository;

import co.com.oaktrees.entity.Persona;
import co.com.oaktrees.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByNombre(String nombre);

    Optional<Producto> findById(String id);

    boolean existsById(String id);

    boolean existsByNombre(String nombre);

    void deleteById(String id);
}
