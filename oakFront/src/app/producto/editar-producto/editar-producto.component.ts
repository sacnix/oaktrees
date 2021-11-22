import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { Categoria } from 'src/app/models/categoria';
import { Producto } from 'src/app/models/producto';
import { CategoriaService } from 'src/app/service/categoria.service';
import { ProductoService } from 'src/app/service/producto.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-editar-producto',
  templateUrl: './editar-producto.component.html',
  styleUrls: ['./editar-producto.component.css']
})
export class EditarProductoComponent implements OnInit {

  @ViewChild('imagenInputFile', { static: false }) imagenFile: ElementRef;

  imagen: File;
  imagenMin: File;
  isLogged = false;
  isLoginFail = false;
  producto: Producto;
  roles: string[] = [];
  mensajeError = '';
  categoria: string;
  categorias: Categoria[] = [];
  categoriaSeleccionada: boolean = false;

  constructor(
    private tokenService: TokenService,
    private productoService: ProductoService,
    private activatedRoute: ActivatedRoute,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService,
    private router: Router,
    private categoriaService: CategoriaService
  ) { }

  ngOnInit(): void {
    const idProducto = this.activatedRoute.snapshot.params.idProducto;
    this.productoService.detalle(idProducto).subscribe(
      data => {
        this.producto = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        this.router.navigate(['/productos-admin']);
      }
    );
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

  onUpdate(): void {
    console.log(this.imagen);
    if(this.imagen==undefined){
      this.imagen = new File([""], this.producto.imagenId);
    }    
    console.log(this.imagen);
    const idProducto = this.activatedRoute.snapshot.params.idProducto;
    this.producto = new Producto(this.producto.nombre, this.producto.descripcion, this.producto.precio, this.producto.color, this.producto.imagenUrl,
      this.producto.imagenId, this.producto.estado, this.producto.visibilidad, this.producto.cantidad, this.categoria);
    this.productoService.update(this.imagen, idProducto, this.producto).subscribe(
      data => {
        this.spinner.hide();
        this.toastr.success('Producto actualizado exitosamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/productos-admin']);
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
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
    this.categoriaSeleccionada = true;
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
