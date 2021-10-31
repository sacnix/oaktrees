package co.com.oaktrees.repository;

import co.com.oaktrees.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

    Optional<Carrito> findById(String idCarrito);

    Optional<Carrito> findByIdUsuario(String idUsuario);

    boolean existsByIdUsuario(String idUsuario);

    boolean existsById(String idCarrito);

    void deleteById(String idCarrito);
}
