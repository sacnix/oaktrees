import { Producto } from "./producto";

export class Pedido {

    idPedido?: number;
    fecha: string;
    valorTotal: number;
    tipoEntrega: string;
    telefono: string;
    direccion: string;
    idEstado: string;
    idUsuario: string;
    comentario: string;
    productos: Array<Producto> = [];

    constructor(valorTotal: number, tipoEntrega: string, telefono: string, direccion:string, idEstado: string, 
        idUsuario: string, productos: Array<Producto>, comentario: string){
        this.valorTotal = valorTotal;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
        this.direccion = direccion;
        this.idEstado = idEstado;
        this.idUsuario = idUsuario;
        this.productos = productos;
        this.comentario = comentario;
    }
}
