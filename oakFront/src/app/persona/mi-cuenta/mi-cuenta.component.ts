import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Persona } from 'src/app/models/persona';
import { PersonaService } from 'src/app/service/persona.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-mi-cuenta',
  templateUrl: './mi-cuenta.component.html',
  styleUrls: ['./mi-cuenta.component.css']
})
export class MiCuentaComponent implements OnInit {

  isLogged = false;
  rol: any = null;
  persona: any = null;
  nombre= '';
  telefono= '';
  clave= '';
  correo= '';

  constructor(
    private tokenService: TokenService,
    private personaService: PersonaService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
    ) { }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.correo = this.tokenService.getUserName();
      this.cargarUsuario();
    } else {
      this.isLogged = false;
    }
  }

  cargarUsuario(): void {
    this.personaService.detail(this.correo).subscribe(
      data => {
        this.persona = data;
      },
      err => {
        console.log(err);
      }
    )
  }

  onUpdate(): void{
    const correo = this.activatedRoute.snapshot.params.correo;
    this.persona = new Persona(this.nombre, this.telefono, this.persona.correo, this.clave);
    this.personaService.cambiarClave(correo, this.persona).subscribe(
      data => {
        this.toastr.success('Se han actualizado los datos de la cuenta', 'OK', {
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
