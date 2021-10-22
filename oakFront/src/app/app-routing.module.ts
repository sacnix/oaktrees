import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetalleRolComponent } from './rol/detalle-rol.component';
import { EditarRolComponent } from './rol/editar-rol.component';
import { ListaRolComponent } from './rol/lista-rol.component';
import { NuevoRolComponent } from './rol/nuevo-rol.component';

const routes: Routes = [
  { path: '', component: ListaRolComponent },
  { path: 'lista', component: ListaRolComponent },
  { path: 'detalle/:id', component: DetalleRolComponent },
  { path: 'nuevo', component: NuevoRolComponent },
  { path: 'editar/:id', component: EditarRolComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
