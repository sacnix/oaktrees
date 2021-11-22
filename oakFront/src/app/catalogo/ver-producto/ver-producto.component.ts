import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Carrito } from 'src/app/models/carrito';
import { Producto } from 'src/app/models/producto';
import { CarritoService } from 'src/app/service/carrito.service';
import { CatalogoService } from 'src/app/service/catalogo.service';
import { ProductoService } from 'src/app/service/producto.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-ver-producto',
  templateUrl: './ver-producto.component.html',
  styleUrls: ['./ver-producto.component.css']
})
export class VerProductoComponent implements OnInit {

  imagen: File;
  imagenMin: File;
  isLogged = false;
  isLoginFail = false;
  producto: Producto;
  roles: string[] = [];
  productos: Array<Producto> = [];
  mensajeError = '';
  isAdmin = false;
  isVendedor = false;
  productoSeleccionado: Producto;
  carrito: Carrito;
  id: number | undefined;
  correo: string;

  constructor(
    private tokenService: TokenService,
    private catalogoService: CatalogoService,
    private activatedRoute: ActivatedRoute,
    private carritoService: CarritoService,
    private productoService: ProductoService,
    private toastr: ToastrService,
    private router: Router

  ) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.correo = this.tokenService.getUserName();
    } else {
      this.isLogged = false;
    }
    this.cargarCarrito();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROL_ADMIN') {
        this.isAdmin = true;
      }
      if (rol === 'ROL_VENDEDOR') {
        this.isVendedor = true;
      }
    });
    const idProducto = this.activatedRoute.snapshot.params.idProducto;
    this.catalogoService.detalle(idProducto).subscribe(
      data => {
        this.producto = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        this.router.navigate(['/catalogo']);
      }
    );

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
