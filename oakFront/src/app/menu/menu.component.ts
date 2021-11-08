import { Component, OnInit } from '@angular/core';
import { Rol } from '../models/rol';
import { PersonaService } from '../service/persona.service';
import { RolService } from '../service/rol.service';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLogged = false;
  rol: Rol[] = [];
  roles: string[] = [];
  persona: any = null;
  correo= '';
  isAdmin = false;
  isVendedor = false;

  constructor(
    private tokenService: TokenService,
    private rolService: RolService,
    private personaService: PersonaService) { }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.correo = this.tokenService.getUserName();
      this.cargarUsuario();
    } else {
      this.isLogged = false;
    }
    this.cargarRoles();
    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach(rol => {
      if (rol === 'ROL_ADMIN') {
        this.isAdmin = true;
      }
      if (rol === 'ROL_VENDEDOR') {
        this.isVendedor = true;
      }
    });
  }

  cargarRoles(): void {
    this.rolService.lista().subscribe(
      data => {
        this.rol = data;
      },
      err => {
        console.log(err);
      }
    )
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


  onLogOut(): void {
    this.tokenService.logOut();
    window.location.reload();
  }

}
