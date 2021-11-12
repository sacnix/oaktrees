import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  productoURL = 'http://localhost:8080/producto/';

  constructor(private httpClient: HttpClient) { }

  public listarProductos(): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(this.productoURL + 'list');
  }

  public detalle(idProducto: number): Observable<Producto> {
    return this.httpClient.get<Producto>(this.productoURL + `detail/${idProducto}`);
  }

  public detailName(nombre: string): Observable<Producto> {
    return this.httpClient.get<Producto>(this.productoURL + `detailname/${nombre}`);
  }

  public save(imagen: File, producto: Producto): Observable<any> {
    const formData = new FormData();
    formData.append('multipartFile', imagen);
    formData.append('nombre', producto.nombre);
    formData.append('descripcion', producto.descripcion);
    formData.append('precio', producto.precio.toString());
    formData.append('color', producto.color);
    formData.append('estado', producto.estado.toString());
    formData.append('visibilidad', producto.visibilidad.toString());
    formData.append('cantidad', producto.cantidad.toString());
    return this.httpClient.post<any>(this.productoURL + 'create', formData);
  }

  public update(imagen: File, idProducto: number, producto: Producto): Observable<any> {
    const formData = new FormData();
    formData.append('multipartFile', imagen);
    formData.append('nombre', producto.nombre);
    formData.append('descripcion', producto.descripcion);
    formData.append('precio', producto.precio.toString());
    formData.append('color', producto.color);
    formData.append('estado', producto.estado.toString());
    formData.append('visibilidad', producto.visibilidad.toString());
    formData.append('cantidad', producto.cantidad.toString());
    return this.httpClient.put<any>(this.productoURL + `update/${idProducto}`, formData);
  }

  public deleteProducto(idProducto: number): Observable<any> {
    return this.httpClient.delete<any>(this.productoURL + `delete/${idProducto}`);
  }
}
