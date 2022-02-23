import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pedido } from '../models/pedido';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  pedidoURL = 'https://oak-trees-spring.herokuapp.com/pedido/';

  constructor(private httpClient: HttpClient) { }

  public detail(idPedido: string): Observable<Pedido> {
    return this.httpClient.get<Pedido>(this.pedidoURL + `detail/${idPedido}`);
  }

  public crear(pedido: Pedido): Observable<any> {
    return this.httpClient.post<any>(this.pedidoURL + 'create', pedido);
  }

  public update(idPedido: number | undefined, pedido: Pedido): Observable<any> {
    return this.httpClient.put<any>(this.pedidoURL + `update/${idPedido}`, pedido);
  }

  public listarPedidos(idUsuario: string): Observable<Pedido[]> {
    return this.httpClient.get<Pedido[]>(this.pedidoURL + `historico/${idUsuario}`);
  }

  public listarPedidosAdmin(): Observable<Pedido[]> {
    return this.httpClient.get<Pedido[]>(this.pedidoURL + `lista`);
  }
}
