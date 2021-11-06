package co.com.oaktrees.util;

import co.com.oaktrees.entity.Rol;
import co.com.oaktrees.enums.RolNombre;
import co.com.oaktrees.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CrearRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        /**Rol rolAdmin = new Rol(RolNombre.ROL_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROL_USER);
        Rol rolVendedor = new Rol(RolNombre.ROL_VENDEDOR);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
        rolService.save(rolVendedor);**/

    }
}
