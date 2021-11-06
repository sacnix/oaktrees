import { Component, OnInit } from '@angular/core';
import {TokenService} from "../../service/token.service";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {LoginUsuario} from "../../models/login-usuario";

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

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router
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
      },
      err =>{
        this.isLogged = false;
        this.isLoginFail = true;
        this.mensajeError = err.error.message;
      }
    );

  }

}
