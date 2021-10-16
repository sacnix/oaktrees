import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Rol } from '../models/rol';
import { RolService } from '../service/rol.service';

@Component({
  selector: 'app-detalle-rol',
  templateUrl: './detalle-rol.component.html',
  styleUrls: ['./detalle-rol.component.css']
})
export class DetalleRolComponent implements OnInit {

  rol: any = null;

  constructor(
    private rolService: RolService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.rolService.detail(id).subscribe(
      data=>{
        this.rol = data;
      },
      err =>{
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        this.goBack();
      }
    );
  }

  goBack(): void{
    this.router.navigate(['/']);
  }

}
