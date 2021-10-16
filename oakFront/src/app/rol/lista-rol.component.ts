import { i18nMetaToJSDoc } from '@angular/compiler/src/render3/view/i18n/meta';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Rol } from '../models/rol';
import { RolService } from '../service/rol.service';

@Component({
  selector: 'app-lista-rol',
  templateUrl: './lista-rol.component.html',
  styleUrls: ['./lista-rol.component.css']
})
export class ListaRolComponent implements OnInit {

  rol: Rol[] = [];

  constructor(private rolService: RolService,
    private toastr: ToastrService
    ) { }

  ngOnInit(): void {
    this.cargarRoles();
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
      data =>{
        this.toastr.success('Producto eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarRoles();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }

}
