package co.com.oaktrees.dto;

public class EstadoPedidoDTO {

    private int idEstado;
    private String nombre;

    public EstadoPedidoDTO() {
    }

    public EstadoPedidoDTO(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
