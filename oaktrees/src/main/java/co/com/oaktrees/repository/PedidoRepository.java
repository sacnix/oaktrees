package co.com.oaktrees.repository;

import co.com.oaktrees.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Optional<Pedido> findById(String idPedido);

    Optional<Pedido> findByIdUsuario(String idUsuario);

    boolean existsByIdUsuario(String idUsuario);

    boolean existsById(String idPedido);

    void deleteById(String idPedido);
}
