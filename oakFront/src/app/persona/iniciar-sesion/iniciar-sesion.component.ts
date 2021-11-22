import { Component, OnInit } from '@angular/core';
import {TokenService} from "../../service/token.service";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {LoginUsuario} from "../../models/login-usuario";
import { Carrito } from 'src/app/models/carrito';
import { CarritoService } from 'src/app/service/carrito.service';
import { Producto } from 'src/app/models/producto';

@Component({
  selector: 'app-iniciar-sesion',
  templateUrl: './iniciar-sesion.component.html',
  styleUrls: ['./iniciar-sesion.component.css']
})
export class IniciarSesionComponent implements OnInit {

  isLogged = false;
  isLoginFail = false;
  loginUsuario: LoginUsuario | undefined;
  correo = '';
  clave = '';
  roles: string[] = [];
  mensajeError= '';
  carrito: Carrito;
  producto: Producto[] = [];

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private carritoService: CarritoService
  ) { }

  ngOnInit() {
    if(this.tokenService.getToken()){
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }
  }

  onLogin(): void{
    this.loginUsuario = new LoginUsuario(this.correo, this.clave);
    this.authService.login(this.loginUsuario).subscribe(
      data =>{
        this.isLogged = true;
        this.isLoginFail = false;

        this.tokenService.setToken(data.token);
        this.tokenService.setUserName(data.correo);
        this.tokenService.setAuthorities(data.authorities);
        this.roles = data.authorities;
        this.router.navigate(['/']);
        this.cargarCarrito();
      },
      err =>{
        this.isLogged = false;
        this.isLoginFail = true;
        this.mensajeError = err.error.message;
      }
    );

  }

  cargarCarrito(): void {
    this.carritoService.detail(this.correo).subscribe(
      data => {
        this.carrito = data;
      },
      err => {
        this.crearCarrito();
      }
    )
  }

  crearCarrito(): void {
    this.carrito = new Carrito(0, 0, this.correo, this.producto);
    this.carritoService.crear(this.carrito).subscribe(
      data => {
        this.carrito = data;
      },
      err => {
        console.log(err);
      }
    )
  }


}
