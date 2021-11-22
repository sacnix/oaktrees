import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Pedido } from 'src/app/models/pedido';
import { PedidoService } from 'src/app/service/pedido.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-listar-pedidos',
  templateUrl: './listar-pedidos.component.html',
  styleUrls: ['./listar-pedidos.component.css']
})
export class ListarPedidosComponent implements OnInit {

  isLogged = false;
  correo = '';
  pedido: Pedido[] = [];

  constructor(
    private pedidoService: PedidoService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.cargarPedidos();
  }

  cargarPedidos(): void {
    this.pedidoService.listarPedidosAdmin().subscribe(
      data => {
        this.pedido = data;
      },
      err => {
        console.log(err);
      }
    )
  }

}
