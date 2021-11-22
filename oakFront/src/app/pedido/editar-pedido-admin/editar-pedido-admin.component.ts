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
  selector: 'app-editar-pedido-admin',
  templateUrl: './editar-pedido-admin.component.html',
  styleUrls: ['./editar-pedido-admin.component.css']
})
export class EditarPedidoAdminComponent implements OnInit {

  isLogged = false;
  rol: any = null;
  correo = '';
  producto: Producto[] = [];
  productos: Array<Producto> = [];
  productoSeleccionado: Producto;
  id: number | undefined;
  envio: string;
  direccion: string;
  telefono: string;
  pedido: Pedido;
  idEstado = '';
  comentario = '';
  cambio = false;


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
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }

  selectChangeHandler (event: any) {
    this.idEstado = event.target.value;
    this.cambio = true;
  }

  onUpdate(): void {

    this.pedido = new Pedido(this.pedido.valorTotal, this.pedido.tipoEntrega, this.pedido.telefono, this.pedido.direccion, this.idEstado, this.pedido.idUsuario, this.pedido.productos, this.pedido.comentario);
    this.pedidoService.update(this.id, this.pedido).subscribe(
      data => {
        this.toastr.success('Pedido actualizado exitosamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/pedidos']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/pedidos']);
      }
    );

  }

}

