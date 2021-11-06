import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginUsuario } from 'src/app/models/login-usuario';
import { Persona } from 'src/app/models/persona';
import { PersonaService } from 'src/app/service/persona.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-cambiar-clave',
  templateUrl: './cambiar-clave.component.html',
  styleUrls: ['./cambiar-clave.component.css']
})
export class CambiarClaveComponent implements OnInit {

  isLogged = false;
  isLoginFail = false;
  loginUsuario: LoginUsuario | undefined;
  clave = '';
  roles: string[] = [];
  mensajeError= '';
  persona: any = null;

  constructor(
    private tokenService: TokenService,
    private personaService: PersonaService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const correo = this.activatedRoute.snapshot.params.correo;
    this.personaService.detail(correo).subscribe(
      data => {
        this.persona = data;
        console.log('id '+ this.persona.nombre);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      }
    );
  }

  onUpdate(): void{
    const correo = this.activatedRoute.snapshot.params.correo;
    console.log('corr '+ correo);
    console.log('corr '+ this.clave);
    this.persona = new Persona(this.persona.nombre, this.persona.telefono, this.persona.correo, this.clave);
    this.personaService.cambiarClave(correo, this.persona).subscribe(
      data => {
        this.toastr.success('Contraseña actualizada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/']);
      },
      err => {
        this.toastr.error(err.error.message, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);

      }
    );
  }

}
