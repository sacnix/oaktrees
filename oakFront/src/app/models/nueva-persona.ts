export class NuevaPersona {
  telefono: string;
  nombre: string;
  correo: string;
  clave: string;
  rol: string

  constructor(nombre: string, telefono: string, correo: string, clave: string, rol: string) {
    this.clave = clave;
    this.correo = correo;
    this.nombre = nombre;
    this.telefono = telefono;
    this.rol = rol;
  }

}
