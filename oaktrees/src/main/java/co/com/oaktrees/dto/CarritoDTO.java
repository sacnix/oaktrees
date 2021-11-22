package co.com.oaktrees.dto;

import co.com.oaktrees.entity.Producto;

import java.util.ArrayList;
import java.util.List;

public class CarritoDTO {

    private float subTotal;
    private float total;
    private String idUsuario;
    private List<Producto> productos = new ArrayList<>();

    public CarritoDTO(float subTotal, float total, String idUsuario) {
        this.subTotal = subTotal;
        this.total = total;
        this.idUsuario = idUsuario;
    }

    public CarritoDTO() {
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
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
