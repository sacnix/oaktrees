package co.com.oaktrees.entity;

import co.com.oaktrees.enums.TipoEntregaNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class TipoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoEntrega;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private TipoEntregaNombre nombre;

    public TipoEntrega() {
    }

    public TipoEntrega(TipoEntregaNombre nombre) {
        this.nombre = nombre;
    }

    public int getIdTipoEntrega() {
        return idTipoEntrega;
    }

    public void setIdTipoEntrega(int idTipoEntrega) {
        this.idTipoEntrega = idTipoEntrega;
    }

    public TipoEntregaNombre getNombre() {
        return nombre;
    }

    public void setNombre(TipoEntregaNombre nombre) {
        this.nombre = nombre;
    }
}
