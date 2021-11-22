import { Producto } from "./producto";

export class Pedido {

    idPedido?: number;
    valorTotal: number;
    tipoEntrega: string;
    telefono: string;
    direccion: string;
    idEstado: number;
    idUsuario: string;
    productos: Array<Producto> = [];

    constructor(valorTotal: number, tipoEntrega: string, telefono: string, direccion:string, idEstado: number, idUsuario: string, productos: Array<Producto>){
        this.valorTotal = valorTotal;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
        this.direccion = direccion;
        this.idEstado = idEstado;
        this.idUsuario = idUsuario;
        this.productos = productos;
    }
}
