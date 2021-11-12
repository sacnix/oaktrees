export class Imagen {
    id?: number;
    name: string;
    imagenUrl: string;
    imagenId: string;

    constructor(name: string, imagenUrl: string, imagenId: string){
        this.name = name;
        this.imagenUrl = imagenUrl;
        this.imagenId = imagenId;
    }
}