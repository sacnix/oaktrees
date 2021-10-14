import { i18nMetaToJSDoc } from '@angular/compiler/src/render3/view/i18n/meta';
import { Component, OnInit } from '@angular/core';
import { Rol } from '../models/rol';
import { RolService } from '../service/rol.service';

@Component({
  selector: 'app-lista-rol',
  templateUrl: './lista-rol.component.html',
  styleUrls: ['./lista-rol.component.css']
})
export class ListaRolComponent implements OnInit {

  rol: Rol[] = [];

  constructor(private rolService: RolService) { }

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

  borrar(id: number) {
    alert('borrar el ' + id);
  }

}
