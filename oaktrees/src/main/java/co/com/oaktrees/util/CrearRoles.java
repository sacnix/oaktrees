package co.com.oaktrees.util;

import co.com.oaktrees.entity.EstadoPedido;
import co.com.oaktrees.entity.Persona;
import co.com.oaktrees.entity.Rol;
import co.com.oaktrees.entity.TipoEntrega;
import co.com.oaktrees.enums.EstadoPedidoNombre;
import co.com.oaktrees.enums.RolNombre;
import co.com.oaktrees.enums.TipoEntregaNombre;
import co.com.oaktrees.service.EstadoPedidoService;
import co.com.oaktrees.service.PersonaService;
import co.com.oaktrees.service.RolService;
import co.com.oaktrees.service.TipoEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CrearRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Autowired
    TipoEntregaService tipoEntregaService;

    @Autowired
    EstadoPedidoService estadoPedidoService;

    @Autowired
    PersonaService personaService;

    @Override
    public void run(String... args) throws Exception {
        /**Rol rolAdmin = new Rol(RolNombre.ROL_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROL_USER);
        Rol rolVendedor = new Rol(RolNombre.ROL_VENDEDOR);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
        rolService.save(rolVendedor);

        TipoEntrega tienda = new TipoEntrega(TipoEntregaNombre.TIENDA);
        TipoEntrega domicilio = new TipoEntrega(TipoEntregaNombre.DOMICILIO);
        tipoEntregaService.save(tienda);
        tipoEntregaService.save(domicilio);

        EstadoPedido estadoEntregado = new EstadoPedido(EstadoPedidoNombre.ENTREGADO);
        EstadoPedido estadoEntrega = new EstadoPedido(EstadoPedidoNombre.EN_ENTREGA);
        EstadoPedido estadoRecibido = new EstadoPedido(EstadoPedidoNombre.RECIBIDO);
        EstadoPedido estadoCancelado = new EstadoPedido(EstadoPedidoNombre.CANCELADO);
        EstadoPedido estadoListo = new EstadoPedido(EstadoPedidoNombre.LISTO);
        estadoPedidoService.save(estadoEntregado);
        estadoPedidoService.save(estadoEntrega);
        estadoPedidoService.save(estadoRecibido);
        estadoPedidoService.save(estadoCancelado);
        estadoPedidoService.save(estadoListo);

        Persona personaAdmin = new Persona("admin", "12345", "admin@gmail.com", "$2a$10$5hIDtX8JHlK9Amw5.idFc.IQgVhKlVZU7h55H6zh2Q88xx.fPEJ2e");
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByNombre(RolNombre.ROL_ADMIN).get());
        personaAdmin.setRoles(roles);
        personaService.save(personaAdmin);**/

    }
}
