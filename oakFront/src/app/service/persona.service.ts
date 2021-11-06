import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Persona } from '../models/persona';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  personaURL = 'http://localhost:8080/usuario/';

  constructor(private httpClient: HttpClient) { }

  public detail(correo: string): Observable<Persona> {
    return this.httpClient.get<Persona>(this.personaURL + `detail/${correo}`);
  }

  public cambiarClave(correo: string, persona: Persona): Observable<any> {
    return this.httpClient.put<any>(this.personaURL + `cambiar-clave/${correo}`, persona);
  }
}
