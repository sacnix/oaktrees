import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pedido } from '../models/pedido';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  pedidoURL = 'http://localhost:8080/pedido/';

  constructor(private httpClient: HttpClient) { }

  public detail(id: string): Observable<Pedido> {
    return this.httpClient.get<Pedido>(this.pedidoURL + `detailname/${id}`);
  }

  public crear(pedido: Pedido): Observable<any> {
    return this.httpClient.post<any>(this.pedidoURL + 'create', pedido);
  }

  public update(id: number | undefined, pedido: Pedido): Observable<any> {
    return this.httpClient.put<any>(this.pedidoURL + `update/${id}`, pedido);
  }
}
