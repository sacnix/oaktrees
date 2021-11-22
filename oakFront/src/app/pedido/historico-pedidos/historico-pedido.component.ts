import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Pedido } from 'src/app/models/pedido';
import { PedidoService } from 'src/app/service/pedido.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-historico-pedido',
  templateUrl: './historico-pedido.component.html',
  styleUrls: ['./historico-pedido.component.css']
})
export class HistoricoPedidoComponent implements OnInit {
  isLogged = false;
  correo = '';
  pedido: Pedido[] = [];

  constructor(
    private pedidoService: PedidoService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.correo = this.tokenService.getUserName();
      this.isLogged = false;
    } else {
      this.isLogged = false;
    }
    this.cargarPedidos();
  }

  cargarPedidos(): void {
    this.pedidoService.listarPedidos(this.correo).subscribe(
      data => {
        this.pedido = data;
      },
      err => {
        console.log(err);
      }
    )
  }

}
