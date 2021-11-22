import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Carrito } from 'src/app/models/carrito';
import { Producto } from 'src/app/models/producto';
import { CarritoService } from 'src/app/service/carrito.service';
import { CatalogoService } from 'src/app/service/catalogo.service';
import { ProductoService } from 'src/app/service/producto.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {
  producto: Producto[] = [];
  productoSeleccionado: Producto;
  productos: Array<Producto> = [];
  isLogged = false;
  carrito: Carrito;
  correo: string;
  id: number | undefined;

  constructor(
    private catalogoService: CatalogoService,
    private carritoService: CarritoService,
    private productoService: ProductoService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.correo = this.tokenService.getUserName();
    } else {
      this.isLogged = false;
    }
    this.cargarProductos();
    this.cargarCarrito();
  }

  cargarProductos(): void {
    this.catalogoService.listarProductos().subscribe(
      data => {
        this.producto = data;
      },
      err => {
        console.log(err);
      }
    )
  }

  cargarCarrito(): void {
    this.carritoService.detail(this.correo).subscribe(
      data => {
        this.carrito = data;
        this.id = this.carrito.idCarrito;
        this.productos = this.carrito.productos;
      },
      err => {
        console.log(err);
      }
    )
  }

  agregarProducto(idProducto: any): void {
    this.productoService.detalle(idProducto).subscribe(
      data => {
        this.productoSeleccionado = data;
        this.productos.push(this.productoSeleccionado);
        console.log(this.productos.length);
        this.carrito = new Carrito(this.carrito.subTotal, this.carrito.total, this.carrito.idUsuario, this.productos);
        this.carritoService.update(this.id, this.carrito).subscribe(
          data => {
            this.toastr.success('El producto se ha agregado al carrito', 'OK', {
              timeOut: 3000, positionClass: 'toast-top-center'
            });
            this.cargarProductos();
          },
          err => {
            this.toastr.error(err.error.mensaje, 'Fail', {
              timeOut: 3000, positionClass: 'toast-top-center',
            });
          }
        );
      },
      err => {
        console.log(err);
      }
    )


  }

}
