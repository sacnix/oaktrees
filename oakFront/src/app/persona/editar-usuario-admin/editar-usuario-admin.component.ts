import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginUsuario } from 'src/app/models/login-usuario';
import { Persona } from 'src/app/models/persona';
import { PersonaService } from 'src/app/service/persona.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-editar-usuario-admin',
  templateUrl: './editar-usuario-admin.component.html',
  styleUrls: ['./editar-usuario-admin.component.css']
})
export class EditarUsuarioAdminComponent implements OnInit {

  isLogged = false;
  isLoginFail = false;
  loginUsuario: LoginUsuario | undefined;
  roles: string[] = [];
  mensajeError= '';
  persona: any = null;
  rol: '';

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
    console.log("this " + this.rol);
    this.persona = new Persona(this.persona.nombre, this.persona.telefono, this.persona.correo, this.persona.clave, this.rol);
    this.personaService.actualizar(correo, this.persona).subscribe(
      
      data => {
        this.toastr.success('La cuenta ha sido actualizada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
          
        });
        this.router.navigate(['/usuarios']);
      },
      err => {
        this.toastr.error(err.error.message, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.router.navigate(['/usuarios']);

      }
    );
  }

  selectChangeHandler (event: any) {
    this.rol = event.target.value;
  }
}
