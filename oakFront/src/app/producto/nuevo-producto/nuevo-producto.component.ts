import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { Producto } from 'src/app/models/producto';
import { AuthService } from 'src/app/service/auth.service';
import { ProductoService } from 'src/app/service/producto.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-nuevo-producto',
  templateUrl: './nuevo-producto.component.html',
  styleUrls: ['./nuevo-producto.component.css']
})
export class NuevoProductoComponent implements OnInit {

  @ViewChild('imagenInputFile', {static: false}) imagenFile: ElementRef;

  imagen: File;
  imagenMin: File;
  producto: Producto;
  isLogged = false;
  nombre: string;
  descripcion: string;
  precio: number;
  color: string;
  estado: number;
  visibilidad: number;
  cantidad: number;
  imagenUrl: string;
  imagenId: string;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private productoService: ProductoService,
    private router: Router,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    if(this.tokenService.getToken()){
      this.isLogged = true;
    }
  }

  onFileChange(event: any) {
    this.imagen = event.target.files[0];
    const fr = new FileReader();
    fr.onload = (evento: any) => {
      this.imagenMin = evento.target.result;
    };
    fr.readAsDataURL(this.imagen);
  }

  onUpload(): void {
    this.spinner.show();
    this.producto = new Producto(this.nombre, this.descripcion, this.precio, this.color, this.imagenUrl, this.imagenId, this.estado,
      this.visibilidad, this.cantidad);
    this.productoService.save(this.imagen,this.producto).subscribe(
      data => {
        this.spinner.hide();
        this.toastr.success('Producto creado exitosamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/productos-admin']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.spinner.hide();
        this.reset();
      }
    );
  }

  reset(): void {
    this.imagenFile.nativeElement.value = '';
  }

}

