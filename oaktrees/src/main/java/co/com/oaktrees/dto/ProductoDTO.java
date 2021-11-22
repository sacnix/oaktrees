package co.com.oaktrees.dto;

public class ProductoDTO {

    private String nombre;
    private String descripcion;
    private float precio;
    private String color;
    private String imagenUrl;
    private String imagenId;
    private int estado;
    private int visibilidad;
    private int cantidad;
    private String categoria;
    private String idCarrito;

    public ProductoDTO(String nombre, String descripcion, float precio, String color, String imagenUrl,
                    String imagenId, int estado, int visibilidad, int cantidad, String categoria, String idCarrito) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.color = color;
        this.imagenUrl = imagenUrl;
        this.imagenId = imagenId;
        this.estado = estado;
        this.visibilidad = visibilidad;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.idCarrito = idCarrito;
    }

    public ProductoDTO() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getImagenId() {
        return imagenId;
    }

    public void setImagenId(String imagenId) {
        this.imagenId = imagenId;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(int visibilidad) {
        this.visibilidad = visibilidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(String idCarrito) {
        this.idCarrito = idCarrito;
    }
}
