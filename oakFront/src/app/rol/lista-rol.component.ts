import { i18nMetaToJSDoc } from '@angular/compiler/src/render3/view/i18n/meta';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Rol } from '../models/rol';
import { RolService } from '../service/rol.service';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-lista-rol',
  templateUrl: './lista-rol.component.html',
  styleUrls: ['./lista-rol.component.css']
})
export class ListaRolComponent implements OnInit {

  rol: Rol[] = [];
  roles: string[] = [];
  isAdmin = false;
  isVendedor = false;

  constructor(private rolService: RolService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
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

  borrar(id: any) {
    this.rolService.delete(id).subscribe(
      data => {
        this.toastr.success('Producto eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarRoles();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

}
