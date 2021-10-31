package co.com.oaktrees.dto;

import java.util.Date;

public class PedidoDTO {

    private String idPedido;
    private Date fecha;
    private float valorTotal;
    private int idTipoEntrega;
    private int idEstado;
    private String idUsuario;
    private String idCarrito;

    public PedidoDTO(Date fecha, float valorTotal, int idTipoEntrega, int idEstado, String idUsuario, String idCarrito) {
        this.fecha = fecha;
        this.valorTotal = valorTotal;
        this.idTipoEntrega = idTipoEntrega;
        this.idEstado = idEstado;
        this.idUsuario = idUsuario;
        this.idCarrito = idCarrito;
    }

    public PedidoDTO() {
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdTipoEntrega() {
        return idTipoEntrega;
    }

    public void setIdTipoEntrega(int idTipoEntrega) {
        this.idTipoEntrega = idTipoEntrega;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(String idCarrito) {
        this.idCarrito = idCarrito;
    }
}
