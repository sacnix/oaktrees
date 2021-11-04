package co.com.oaktrees.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PersonaPrincipal implements UserDetails {

    private String nombre;
    private String telefono;
    private String correo;
    private String clave;
    private Collection<? extends GrantedAuthority> authorities;

    public PersonaPrincipal(String nombre, String telefono, String correo, String clave, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
        this.authorities = authorities;
    }

    public static PersonaPrincipal build(Persona persona){
        List<GrantedAuthority> authorities =
                persona.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        return new PersonaPrincipal(persona.getNombre(), persona.getTelefono(), persona.getCorreo(),
                persona.getClave(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
}
