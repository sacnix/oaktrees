import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Carrito } from 'src/app/models/carrito';
import { Producto } from 'src/app/models/producto';
import { CarritoService } from 'src/app/service/carrito.service';
import { ProductoService } from 'src/app/service/producto.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-ver-carrito',
  templateUrl: './ver-carrito.component.html',
  styleUrls: ['./ver-carrito.component.css']
})
export class VerCarritoComponent implements OnInit {

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

  constructor(
    private tokenService: TokenService,
    private carritoService: CarritoService,
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

  eliminarProducto(idProducto: any): void {
    this.producto.forEach((value,index)=>{
      if(value.idProducto==idProducto) this.producto.splice(index,1);
    });

    this.productos = this.producto;
      this.carrito = new Carrito(this.carrito.subTotal, this.carrito.total, this.carrito.idUsuario, this.productos);
      this.carritoService.update(this.id, this.carrito).subscribe(
        data => {
          this.toastr.success('El carrito ha sido actualizado', 'OK', {
            timeOut: 3000, positionClass: 'toast-top-center'
          });
          this.cargarCarrito();
          this.cargarTotales();
        },
        err => {
          this.toastr.error(err.error.mensaje, 'Fail', {
            timeOut: 3000, positionClass: 'toast-top-center',
          });
        }
      );

    }

}
