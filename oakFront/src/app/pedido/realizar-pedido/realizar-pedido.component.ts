import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Carrito } from 'src/app/models/carrito';
import { Pedido } from 'src/app/models/pedido';
import { Producto } from 'src/app/models/producto';
import { CarritoService } from 'src/app/service/carrito.service';
import { PedidoService } from 'src/app/service/pedido.service';
import { ProductoService } from 'src/app/service/producto.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-realizar-pedido',
  templateUrl: './realizar-pedido.component.html',
  styleUrls: ['./realizar-pedido.component.css']
})
export class RealizarPedidoComponent implements OnInit {

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
  cambio= false;
  pedido: Pedido;
  idEstado = 1;

  constructor(
    private tokenService: TokenService,
    private carritoService: CarritoService,
    private pedidoService: PedidoService,
    private productoService: ProductoService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.correo = this.tokenService.getUserName();
      this.cargarCarrito();
    } else {
      this.isLogged = false;
    }
  }

  cargarCarrito(): void {
    this.carritoService.detail(this.correo).subscribe(
      data => {
        this.carrito = data;
        this.producto = this.carrito.productos;
        this.id = this.carrito.idCarrito;
        this.cargarTotales();
      },
      err => {
        console.log(err);
      }
    )
  }

  cargarTotales(): void {
    this.subTotal = 0;
    this.total = 0;
    this.carrito.productos.forEach((value,index)=>{
      this.subTotal = this.subTotal + value.precio;
      this.total = this.total + value.precio;
    });
    this.carrito.subTotal = this.subTotal;
    this.carrito.total = this.total;
  }

  keyPressNumbers(event: any) {
    var charCode = (event.which) ? event.which : event.keyCode;
    // Only Numbers 0-9
    if ((charCode < 48 || charCode > 57)) {
      event.preventDefault();
      return false;
    } else {
      return true;
    }
  }

  selectChangeHandler (event: any) {
    this.envio = event.target.value;
    this.cambio = true;
  }

  onUpload(): void {

    this.pedido = new Pedido(this.total, this.envio, this.telefono, this.direccion, this.idEstado, this.carrito.idUsuario, this.producto);
    console.log("aqui "+ this.pedido.direccion);
    console.log("aqui "+ this.producto.length);
    this.pedidoService.crear(this.pedido).subscribe(
      data => {
        this.toastr.success('Pedido creado exitosamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/catalogo']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/catalogo']);
      }
    );

  }

}
