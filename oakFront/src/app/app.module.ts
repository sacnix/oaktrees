import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaRolComponent } from './rol/lista-rol.component';
import { DetalleRolComponent } from './rol/detalle-rol.component';
import { NuevoRolComponent } from './rol/nuevo-rol.component';
import { EditarRolComponent } from './rol/editar-rol.component';

//para consumar API y el formulario
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

//externos
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';


@NgModule({
  declarations: [
    AppComponent,
    ListaRolComponent,
    DetalleRolComponent,
    NuevoRolComponent,
    EditarRolComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
