package co.com.oaktrees.dto;

import co.com.oaktrees.entity.Producto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoDTO {

    private Date fecha;
    private float valorTotal;
    private String tipoEntrega;
    private String telefono;
    private String direccion;
    private String idEstado;
    private String idUsuario;
    private String comentario;
    private List<Producto> productos = new ArrayList<>();

    public PedidoDTO(Date fecha, float valorTotal, String tipoEntrega, String telefono, String direccion, String idEstado,
                  String idUsuario, String comentario) {
        this.fecha = fecha;
        this.valorTotal = valorTotal;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
        this.direccion = direccion;
        this.idEstado = idEstado;
        this.idUsuario = idUsuario;
        this.comentario = comentario;
    }

    public PedidoDTO() {
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

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
