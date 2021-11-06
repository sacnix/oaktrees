import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NuevaPersona} from "../models/nueva-persona";
import {Observable} from "rxjs";
import {LoginUsuario} from "../models/login-usuario";
import {JwtDTO} from "../models/jwt-dto";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authURL = 'http://localhost:8080/usuario/auth/';

  constructor(private httpClient: HttpClient) { }

  public nuevo(nuevoUsuario: NuevaPersona): Observable<any>{
    return this.httpClient.post<any>(this.authURL + 'nuevo', nuevoUsuario);
  }

  public login(loginUsuario: LoginUsuario): Observable<JwtDTO>{
    return this.httpClient.post<JwtDTO>(this.authURL + 'login', loginUsuario);
  }
}
