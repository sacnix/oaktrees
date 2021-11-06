export class JwtDTO {
  token: string;
  type: string;
  correo: string;
  authorities: string[];
  constructor(token: string, type: string, correo:string, authorities: string[]) {
    this.token = token;
    this.type = type;
    this.correo = correo;
    this.authorities = authorities;
  }
}
