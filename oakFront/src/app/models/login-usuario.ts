export class LoginUsuario {
  correo: string;
  clave: string;

  constructor(correo: string, clave: string) {
    this.correo = correo;
    this.clave = clave;
  }
}
