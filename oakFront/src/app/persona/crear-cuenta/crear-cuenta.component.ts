import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginUsuario } from 'src/app/models/login-usuario';
import { NuevaPersona } from 'src/app/models/nueva-persona';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-crear-cuenta',
  templateUrl: './crear-cuenta.component.html',
  styleUrls: ['./crear-cuenta.component.css']
})
export class CrearCuentaComponent implements OnInit {

  isRegister = false;
  isRegisterFail = false;
  nuevoUsuario: NuevaPersona | undefined;
  nombre = '';
  telefono = '';
  correo = '';
  clave = '';
  mensajeError= '';
  isLogged = false;
  rol = '';

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    if(this.tokenService.getToken()){
      this.isLogged = true;
    }
  }

  onRegister(): void{
    this.rol = '2';
    this.nuevoUsuario = new NuevaPersona(this.nombre, this.telefono, this.correo, this.clave, this.rol);
    this.authService.nuevo(this.nuevoUsuario).subscribe(
      data =>{
        this.isRegister = true;
        this.isRegisterFail = false;
        this.toastr.success('Cuenta creada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/login']);
      },
      err =>{
        this.isRegister = false;
        this.isRegisterFail = true;
        this.mensajeError = err.error.mensaje;
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );

  }

}
