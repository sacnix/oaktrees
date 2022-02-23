import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Carrito } from '../models/carrito';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  carritoURL = 'https://oak-trees-spring.herokuapp.com/carrito/';


  constructor(private httpClient: HttpClient) { }

  public detail(correo: string): Observable<Carrito> {
    return this.httpClient.get<Carrito>(this.carritoURL + `detailname/${correo}`);
  }

  public crear(carrito: Carrito): Observable<any> {
    return this.httpClient.post<any>(this.carritoURL + 'create', carrito);
  }

  public update(id: number | undefined, carrito: Carrito): Observable<any> {
    return this.httpClient.put<any>(this.carritoURL + `update/${id}`, carrito);
  }
}
