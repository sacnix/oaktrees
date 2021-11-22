import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Categoria } from 'src/app/models/categoria';
import { CategoriaService } from 'src/app/service/categoria.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-categorias-admin',
  templateUrl: './categorias-admin.component.html',
  styleUrls: ['./categorias-admin.component.css']
})
export class CategoriasAdminComponent implements OnInit {

  categoria: Categoria[] = [];
  categorias: string[] = [];

  constructor(
    private categoriaService: CategoriaService,
    private toastr: ToastrService,
    private tokenService: TokenService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(): void {
    this.categoriaService.listarCategorias().subscribe(
      data => {
        this.categoria = data;
      },
      err => {
        console.log(err);
      }
    )
  }

  borrar(idProducto: any) {
    this.categoriaService.delete(idProducto).subscribe(
      data => {
        this.toastr.success('La categoría ha sido eliminada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarCategorias();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        this.router.navigate(['/categorias']);
      }
    );
  }

}
