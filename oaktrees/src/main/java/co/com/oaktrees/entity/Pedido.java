package co.com.oaktrees.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;
    private Date fecha;
    private float valorTotal;
    private int idTipoEntrega;
    private int idEstado;
    private String idUsuario;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "carrito_pedido", joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "carrito_id"))
    private List<Producto> productos = new ArrayList<>();

    public Pedido(Date fecha, float valorTotal, int idTipoEntrega, int idEstado, String idUsuario) {
        this.fecha = fecha;
        this.valorTotal = valorTotal;
        this.idTipoEntrega = idTipoEntrega;
        this.idEstado = idEstado;
        this.idUsuario = idUsuario;
    }

    public Pedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
