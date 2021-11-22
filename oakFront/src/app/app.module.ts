import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaRolComponent } from './rol/lista-rol.component';
import { DetalleRolComponent } from './rol/detalle-rol.component';
import { NuevoRolComponent } from './rol/nuevo-rol.component';
import { EditarRolComponent } from './rol/editar-rol.component';
import { interceporProvider } from './interceptors/prod-interceptor.service';
import { ListaPrincipalComponent } from './principal/lista-principal/lista-principal.component';
import { ListarComponent } from './catalogo/listar/listar.component';
import { CambiarClaveComponent } from './persona/cambiar-clave/cambiar-clave.component';
import { CrearCuentaComponent } from './persona/crear-cuenta/crear-cuenta.component';
import { EditarUsuarioAdminComponent } from './persona/editar-usuario-admin/editar-usuario-admin.component';
import { EliminarCuentaComponent } from './persona/eliminar-cuenta/eliminar-cuenta.component';
import { IniciarSesionComponent } from './persona/iniciar-sesion/iniciar-sesion.component';
import { MiCuentaComponent } from './persona/mi-cuenta/mi-cuenta.component';
import { NuevoUsuarioAdminComponent } from './persona/nuevo-usuario-admin/nuevo-usuario-admin.component';
import { RecordarClaveComponent } from './persona/recordar-clave/recordar-clave.component';
import { UsuariosComponent } from './persona/listar-usuarios/usuarios.component';
import { CategoriasAdminComponent } from './categoria/categorias-admin/categorias-admin.component';
import { EditarCategoriaAdminComponent } from './categoria/editar-categoria-admin/editar-categoria-admin.component';
import { NuevaCategoriaComponent } from './categoria/nueva-categoria/nueva-categoria.component';
import { DetalleProductoComponent } from './producto/detalle-producto/detalle-producto.component';
import { EditarProductoComponent } from './producto/editar-producto/editar-producto.component';
import { DetallePedidoComponent } from './pedido/detalle-pedido/detalle-pedido.component';
import { HistoricoPedidoComponent } from './pedido/historico-pedidos/historico-pedido.component';
import { EditarPedidoAdminComponent } from './pedido/editar-pedido-admin/editar-pedido-admin.component';
import { EditarPedidoComponent } from './pedido/editar-pedido/editar-pedido.component';
import { ListarPedidosComponent } from './pedido/listar-pedidos/listar-pedidos.component';
import { MenuComponent } from './menu/menu.component';
import { NuevoProductoComponent } from './producto/nuevo-producto/nuevo-producto.component';

//para consumar API y el formulario
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

//externos
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

import { NgxSpinnerModule } from 'ngx-spinner';
import { ProductosAdminComponent } from './producto/productos-admin/productos-admin.component';
import { VerProductoComponent } from './catalogo/ver-producto/ver-producto.component';
import { VerCarritoComponent } from './carrito/ver-carrito/ver-carrito.component';

@NgModule({
  declarations: [
    AppComponent,
    ListaRolComponent,
    DetalleRolComponent,
    NuevoRolComponent,
    EditarRolComponent,
    ListaPrincipalComponent,
    ListarComponent,
    CambiarClaveComponent,
    CrearCuentaComponent,
    EditarUsuarioAdminComponent,
    EliminarCuentaComponent,
    IniciarSesionComponent,
    MiCuentaComponent,
    NuevoUsuarioAdminComponent,
    RecordarClaveComponent,
    UsuariosComponent,
    CategoriasAdminComponent,
    EditarCategoriaAdminComponent,
    NuevaCategoriaComponent,
    DetalleProductoComponent,
    EditarProductoComponent,
    DetallePedidoComponent,
    HistoricoPedidoComponent,
    EditarPedidoAdminComponent,
    EditarPedidoComponent,
    ListarPedidosComponent,
    MenuComponent,
    NuevoProductoComponent,
    ProductosAdminComponent,
    VerProductoComponent,
    VerCarritoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule,
    NgxSpinnerModule,
    BrowserAnimationsModule
  ],
  providers: [interceporProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
