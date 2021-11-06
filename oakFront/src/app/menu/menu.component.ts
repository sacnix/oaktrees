import { Component, OnInit } from '@angular/core';
import { PersonaService } from '../service/persona.service';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLogged = false;
  rol: any = null;
  persona: any = null;
  correo= '';

  constructor(
    private tokenService: TokenService,
    private personaService: PersonaService) { }

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


  onLogOut(): void {
    this.tokenService.logOut();
    window.location.reload();
  }

}
