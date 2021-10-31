package co.com.oaktrees.dto;

public class CarritoDTO {

    private String idCarrito;
    private float subTotal;
    private float total;
    private String idUsuario;

    public CarritoDTO(float subTotal, float total, String idUsuario) {
        this.subTotal = subTotal;
        this.total = total;
        this.idUsuario = idUsuario;
    }

    public CarritoDTO() {
    }

    public String getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(String idCarrito) {
        this.idCarrito = idCarrito;
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

}
