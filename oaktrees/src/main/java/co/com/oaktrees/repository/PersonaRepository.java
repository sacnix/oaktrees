package co.com.oaktrees.repository;

import co.com.oaktrees.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    Optional<Persona> findByNombre(String nombre);

    Optional<Persona> findByIdUsuario(String idUsuario);

    boolean existsByIdUsuario(String idUsuario);

    boolean existsByNombre(String nombre);

    void deleteByIdUsuario(String idUsuario);
}
