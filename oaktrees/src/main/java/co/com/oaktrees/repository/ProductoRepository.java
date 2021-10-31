package co.com.oaktrees.repository;

import co.com.oaktrees.entity.Persona;
import co.com.oaktrees.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByNombre(String nombre);

    Optional<Producto> findByIdProducto(int idProducto);

    boolean existsByIdProducto(int id);

    boolean existsByNombre(String nombre);

    void deleteByIdProducto(int id);
}
