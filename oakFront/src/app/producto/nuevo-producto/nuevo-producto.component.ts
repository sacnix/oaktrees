import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { Categoria } from 'src/app/models/categoria';
import { Producto } from 'src/app/models/producto';
import { AuthService } from 'src/app/service/auth.service';
import { CategoriaService } from 'src/app/service/categoria.service';
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
  categoria: string;
  categorias: Categoria[] = [];

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private productoService: ProductoService,
    private router: Router,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService,
    private categoriaService: CategoriaService
  ) { }

  ngOnInit() {
    if(this.tokenService.getToken()){
      this.isLogged = true;
    }
    this.categoriaService.listarCategorias().subscribe(
      data => {
        this.categorias = data;
      },
      err => {
        console.log(err);
      }
    )
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
      this.visibilidad, this.cantidad, this.categoria);
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

  selectChangeHandler (event: any) {
    this.categoria = event.target.value;
  }

  keyPressNumbers(event: any) {
    var charCode = (event.which) ? event.which : event.keyCode;
    // Only Numbers 0-9
    if ((charCode < 48 || charCode > 57)) {
      event.preventDefault();
      return false;
    } else {
      return true;
    }
  }

  keyPressAlphaNumeric(event: any) {

    var inp = String.fromCharCode(event.keyCode);

    if (/[a-zA-Z0-9]/.test(inp)) {
      return true;
    } else {
      event.preventDefault();
      return false;
    }
  }

  keyPressAlpha(event: any) {

    var inp = String.fromCharCode(event.keyCode);

    if (/[a-zA-Z]/.test(inp)) {
      return true;
    } else {
      event.preventDefault();
      return false;
    }
  }

}

