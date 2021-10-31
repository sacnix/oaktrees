package co.com.oaktrees.repository;

import co.com.oaktrees.entity.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Integer> {

    Optional<EstadoPedido> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
