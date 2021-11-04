package co.com.oaktrees.repository;

import co.com.oaktrees.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    Optional<Persona> findByNombre(String nombre);

    Optional<Persona> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    boolean existsByNombre(String nombre);

    void deleteByCorreo(String correo);
}
