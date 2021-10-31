import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetalleRolComponent } from './rol/detalle-rol.component';
import { EditarRolComponent } from './rol/editar-rol.component';
import { ListaRolComponent } from './rol/lista-rol.component';
import { NuevoRolComponent } from './rol/nuevo-rol.component';
import {ListaPrincipalComponent} from "./principal/lista-principal/lista-principal.component";


const routes: Routes = [
  { path: '', component: ListaPrincipalComponent },
  { path: '', component: ListaPrincipalComponent },
  { path: 'rol/lista', component: ListaRolComponent },
  { path: 'rol/detalle/:id', component: DetalleRolComponent },
  { path: 'rol/nuevo', component: NuevoRolComponent },
  { path: 'rol/editar/:id', component: EditarRolComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
