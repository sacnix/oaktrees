package co.com.oaktrees.repository;

import co.com.oaktrees.entity.TipoEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoEntregaRepository extends JpaRepository<TipoEntrega, Integer> {

    Optional<TipoEntrega> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
