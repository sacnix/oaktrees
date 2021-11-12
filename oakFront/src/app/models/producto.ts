export class Producto {
    idProducto?: number;
    nombre: string;
    descripcion: string;
    precio: number;
    color: string;
    imagenUrl: string;
    imagenId: string;
    estado: number;
    visibilidad: number;
    cantidad: number;

     constructor(nombre: string, descripcion: string, precio: number, color: string, imagenUrl: string, imagenId: string, estado: number,
        visibilidad: number, cantidad: number) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.color = color;
        this.imagenUrl = imagenUrl;
        this.imagenId = imagenId;
        this.estado = estado;
        this.visibilidad = visibilidad;
        this.cantidad = cantidad;
    }
}
