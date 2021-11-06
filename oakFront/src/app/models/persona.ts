export class Persona {
    telefono: string;
    nombre: string;
    correo: string;
    clave: string;

    constructor(nombre: string, telefono: string, correo: string, clave: string) {
        this.clave = clave;
        this.correo = correo;
        this.nombre = nombre;
        this.telefono = telefono;
    }
}
