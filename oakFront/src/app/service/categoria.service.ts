import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  categoriaURL = 'http://localhost:8080/categoria/';

  constructor(private httpClient: HttpClient) { }

  public listarCategorias(): Observable<Categoria[]> {
    return this.httpClient.get<Categoria[]>(this.categoriaURL + 'lista');
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.categoriaURL + `delete/${id}`);
  }

  public guardar(categoria: Categoria): Observable<any> {
    return this.httpClient.post<any>(this.categoriaURL + 'create', categoria);
  }
}
