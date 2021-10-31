package co.com.oaktrees.dto;

public class TipoEntregaDTO {

    private int idTipoEntrega;
    private String nombre;

    public TipoEntregaDTO() {
    }

    public TipoEntregaDTO(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTipoEntrega() {
        return idTipoEntrega;
    }

    public void setIdTipoEntrega(int idTipoEntrega) {
        this.idTipoEntrega = idTipoEntrega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
