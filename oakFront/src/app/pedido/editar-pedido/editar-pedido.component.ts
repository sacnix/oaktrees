import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Carrito } from 'src/app/models/carrito';
import { Pedido } from 'src/app/models/pedido';
import { Producto } from 'src/app/models/producto';
import { CarritoService } from 'src/app/service/carrito.service';
import { PedidoService } from 'src/app/service/pedido.service';
import { ProductoService } from 'src/app/service/producto.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-editar-pedido',
  templateUrl: './editar-pedido.component.html',
  styleUrls: ['./editar-pedido.component.css']
})
export class EditarPedidoComponent implements OnInit {

  isLogged = false;
  rol: any = null;
  correo = '';
  carrito: Carrito;
  producto: Producto[] = [];
  productos: Array<Producto> = [];
  productoSeleccionado: Producto;
  total = 0;
  subTotal= 0;
  id: number | undefined;
  envio: string;
  direccion: string;
  telefono: string;
  pedido: Pedido;
  idEstado = 'Recibido';
  comentario = '';
  cancelacion = true;


  constructor(
    private tokenService: TokenService,
    private carritoService: CarritoService,
    private pedidoService: PedidoService,
    private productoService: ProductoService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const idPedido = this.activatedRoute.snapshot.params.idPedido;
    this.pedidoService.detail(idPedido).subscribe(
      data => {
        this.pedido = data;
        this.producto = this.pedido.productos;
        this.id = this.pedido.idPedido;
        this.validarCancelacion();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }

  validarCancelacion(): void{
    if(this.pedido.idEstado == 'En entrega' || this.pedido.idEstado == 'Entregado' || this.pedido.idEstado == 'Cancelado'){
      this.cancelacion = false;
    }else{
      this.cancelacion = true;
    }
  }

  onUpdate(): void {
    this.idEstado = 'Cancelado';
    this.pedido = new Pedido(this.pedido.valorTotal, this.pedido.tipoEntrega, this.pedido.telefono, this.pedido.direccion, this.idEstado, this.pedido.idUsuario, this.pedido.productos, this.pedido.comentario);
    this.pedidoService.update(this.id, this.pedido).subscribe(
      data => {
        this.toastr.success('Pedido cancelado exitosamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );

  }

}
