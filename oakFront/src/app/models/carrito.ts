import { Producto } from "./producto";

export class Carrito {
    idCarrito?: number;
    subTotal: number;
    total: number;
    idUsuario: string;
    productos: Array<Producto> = [];

    constructor(subTotal: number, total: number, idUsuario: string, productos: Array<Producto>){
        this.subTotal = subTotal;
        this.total = total;
        this.idUsuario = idUsuario;
        this.productos = productos;
    }
}
