import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetalleRolComponent } from './rol/detalle-rol.component';
import { EditarRolComponent } from './rol/editar-rol.component';
import { ListaRolComponent } from './rol/lista-rol.component';
import { NuevoRolComponent } from './rol/nuevo-rol.component';
import { ListaPrincipalComponent } from "./principal/lista-principal/lista-principal.component";
import { IniciarSesionComponent } from "./persona/iniciar-sesion/iniciar-sesion.component";
import { CrearCuentaComponent } from "./persona/crear-cuenta/crear-cuenta.component";
import { ProdGuardService as guard } from './guards/prod-guard.service';
import { CambiarClaveComponent } from './persona/cambiar-clave/cambiar-clave.component';
import { MiCuentaComponent } from './persona/mi-cuenta/mi-cuenta.component';
import { ListarComponent } from './catalogo/listar/listar.component';
import { UsuariosComponent } from './persona/listar-usuarios/usuarios.component';
import { EditarUsuarioAdminComponent } from './persona/editar-usuario-admin/editar-usuario-admin.component';
import { NuevoUsuarioAdminComponent } from './persona/nuevo-usuario-admin/nuevo-usuario-admin.component';
import { NuevoProductoComponent } from './producto/nuevo-producto/nuevo-producto.component';
import { ProductosAdminComponent } from './producto/productos-admin/productos-admin.component';
import { EditarProductoComponent } from './producto/editar-producto/editar-producto.component';
import { HistoricoPedidoComponent } from './pedido/historico-pedidos/historico-pedido.component';
import { CategoriasAdminComponent } from './categoria/categorias-admin/categorias-admin.component';
import { VerProductoComponent } from './catalogo/ver-producto/ver-producto.component';
import { NuevaCategoriaComponent } from './categoria/nueva-categoria/nueva-categoria.component';
import { EditarCategoriaAdminComponent } from './categoria/editar-categoria-admin/editar-categoria-admin.component';
import { VerCarritoComponent } from './carrito/ver-carrito/ver-carrito.component';
import { RealizarPedidoComponent } from './pedido/realizar-pedido/realizar-pedido.component';
import { EditarPedidoComponent } from './pedido/editar-pedido/editar-pedido.component';
import { ListarPedidosComponent } from './pedido/listar-pedidos/listar-pedidos.component';
import { EditarPedidoAdminComponent } from './pedido/editar-pedido-admin/editar-pedido-admin.component';

const routes: Routes = [
  { path: '', component: ListaPrincipalComponent },
  { path: 'login', component: IniciarSesionComponent },
  { path: 'registro', component: CrearCuentaComponent },
  { path: 'usuarios', component: UsuariosComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'productos', component: NuevoProductoComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'productos-admin', component: ProductosAdminComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'editar-productos/:idProducto', component: EditarProductoComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'editar-usuario/:correo', component: EditarUsuarioAdminComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'crear-usuario', component: NuevoUsuarioAdminComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'cambiar-clave/:correo', component: CambiarClaveComponent, canActivate: [guard], data: { expectedRol: ['admin', 'vendedor', 'user']  }},
  { path: 'mi-cuenta/:correo', component: MiCuentaComponent, canActivate: [guard], data: { expectedRol: ['admin', 'vendedor', 'user']  }},
  { path: 'rol/lista', component: ListaRolComponent, canActivate: [guard], data: { expectedRol: ['admin', 'vendedor', 'user'] } },
  { path: 'rol/detalle/:id', component: DetalleRolComponent, canActivate: [guard], data: { expectedRol: ['admin', 'vendedor', 'user'] } },
  { path: 'rol/nuevo', component: NuevoRolComponent, canActivate: [guard], data: { expectedRol: ['admin', 'vendedor'] } },
  { path: 'rol/editar/:id', component: EditarRolComponent, canActivate: [guard], data: { expectedRol: ['admin', 'vendedor'] } },
  { path: 'catalogo', component: ListarComponent },
  { path: 'ver-producto/:idProducto', component: VerProductoComponent },
  { path: 'historico-pedidos/:correo', component: HistoricoPedidoComponent, canActivate: [guard], data: { expectedRol: ['user'] }  },
  { path: 'realizar-pedido/:idCarrito', component: RealizarPedidoComponent, canActivate: [guard], data: { expectedRol: ['user'] }  },
  { path: 'ver-pedido/:idPedido', component: EditarPedidoComponent, canActivate: [guard], data: { expectedRol: ['user'] }  },
  { path: 'categorias', component: CategoriasAdminComponent, canActivate: [guard], data: { expectedRol: ['admin', 'vendedor'] }  },
  { path: 'pedidos', component: ListarPedidosComponent, canActivate: [guard], data: { expectedRol: ['admin', 'vendedor'] }  },
  { path: 'editar-pedido/:idPedido', component: EditarPedidoAdminComponent, canActivate: [guard], data: { expectedRol: ['admin', 'vendedor'] }  },
  { path: 'categorias/nueva-categoria', component: NuevaCategoriaComponent, canActivate: [guard], data: { expectedRol: ['admin'] }  },
  { path: 'categorias/editar-categoria/:id', component: EditarCategoriaAdminComponent, canActivate: [guard], data: { expectedRol: ['admin'] }  },
  { path: 'carrito/:id', component: VerCarritoComponent, canActivate: [guard], data: { expectedRol: ['vendedor', 'user'] } },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
