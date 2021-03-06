package co.com.oaktrees.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PersonaDTO {

    @NotBlank
    private String nombre;
    @NotBlank
    private String telefono;
    @NotBlank
    @Email
    private String correo;
    @NotBlank
    private String clave;


    public PersonaDTO() {
    }

    public PersonaDTO(String nombre, String telefono, String correo, String clave) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
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
}
