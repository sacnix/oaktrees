import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class CatalogoService {

  productoURL = 'https://oak-trees-spring.herokuapp.com/usuario/auth/catalogo/';

  constructor(private httpClient: HttpClient) { }

  public listarProductos(): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(this.productoURL + 'lista');
  }

  public listarFiltros(): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(this.productoURL + 'listar-filtros');
  }

  public listarCategorias(categoria: string): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(this.productoURL + `lista-categoria/${categoria}`);
  }
  public detalle(idProducto: number): Observable<Producto> {
    return this.httpClient.get<Producto>(this.productoURL + `detalle/${idProducto}`);
  }

}
