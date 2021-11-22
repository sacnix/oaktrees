import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Producto } from 'src/app/models/producto';
import { CatalogoService } from 'src/app/service/catalogo.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-ver-producto',
  templateUrl: './ver-producto.component.html',
  styleUrls: ['./ver-producto.component.css']
})
export class VerProductoComponent implements OnInit {

  imagen: File;
  imagenMin: File;
  isLogged = false;
  isLoginFail = false;
  producto: Producto;
  roles: string[] = [];
  mensajeError = '';


  constructor(
    private tokenService: TokenService,
    private catalogoService: CatalogoService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router

  ) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
    const idProducto = this.activatedRoute.snapshot.params.idProducto;
    this.catalogoService.detalle(idProducto).subscribe(
      data => {
        this.producto = data;
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
        this.router.navigate(['/catalogo']);
      }
    );

  }

}
