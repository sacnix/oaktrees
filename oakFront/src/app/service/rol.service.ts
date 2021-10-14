import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rol } from '../models/rol';

@Injectable({
  providedIn: 'root'
})
export class RolService {

  rolURL = 'http://localhost:8080/rol/';

  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Rol[]> {
    return this.httpClient.get<Rol[]>(this.rolURL + 'lista');
  }

  public detail(id: number): Observable<Rol> {
    return this.httpClient.get<Rol>(this.rolURL + `detail/${id}`);
  }

  public detailName(nombre: string): Observable<Rol> {
    return this.httpClient.get<Rol>(this.rolURL + `detailname/${nombre}`);
  }

  public save(rol: Rol): Observable<any> {
    return this.httpClient.post<any>(this.rolURL + 'create', rol);
  }

  public update(id: number, rol: Rol): Observable<any> {
    return this.httpClient.put<any>(this.rolURL + `update/${id}`, rol);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.rolURL + `delete/${id}`);
  }

}
