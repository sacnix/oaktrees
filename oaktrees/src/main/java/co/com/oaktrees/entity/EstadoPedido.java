package co.com.oaktrees.entity;

import co.com.oaktrees.enums.EstadoPedidoNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class EstadoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstado;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private EstadoPedidoNombre nombre;

    public EstadoPedido() {
    }

    public EstadoPedido(EstadoPedidoNombre nombre) {
        this.nombre = nombre;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoPedidoNombre getNombre() {
        return nombre;
    }

    public void setNombre(EstadoPedidoNombre nombre) {
        this.nombre = nombre;
    }
}
