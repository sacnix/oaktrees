package co.com.oaktrees.entity;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Persona {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String nombre;
    @NotNull
    private String telefono;
    @NotNull
    @Column(unique = true)
    private String correo;
    @NotNull
    private String clave;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Persona() {
    }

    public Persona(String nombre, String telefono, String correo, String clave) {
        this.clave = clave;
        this.correo = correo;
        this.nombre = nombre;
        this.telefono = telefono;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
