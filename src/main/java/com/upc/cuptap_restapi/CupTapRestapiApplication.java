package com.upc.cuptap_restapi;

import com.upc.cuptap_restapi.Models.Entities.Admin;
import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Services.Service.AdminService;
import com.upc.cuptap_restapi.Services.Service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CupTapRestapiApplication implements CommandLineRunner {


    UsuarioService usuarioService;

    AdminService adminService;

    public CupTapRestapiApplication(UsuarioService usuarioService, AdminService adminService) {
        this.usuarioService = usuarioService;
        this.adminService = adminService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CupTapRestapiApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("asd");
        usuario.setCedula("4354y3");
        usuario.setApellidos("asd");
        usuario.setTelefono("vcxs34");
        usuario.setUsername("sdg2345");
        usuario.setPassword("dfgh456");
        Admin admin = new Admin();
        admin.setNombre("aisaac");
        admin.setCedula("2p423t");
        admin.setApellidos("asd");
        admin.setTelefono("gasdfn5y");
        admin.setUsername("32hgfdgt56");
        admin.setPassword("erw234");
        var a = usuarioService.Read().GetAll();

        System.out.println("Admins");
        if (adminService.Read().GetAll().isSuccess())
            for (var item: adminService.Read().GetAll().getData()) {
            System.out.println(item.getNombre());
            }
        System.out.println("a = " + adminService.Read().GetAll().getMsg());
        System.out.println("Usuarios");
        if (a.isSuccess())
            for (var item: a.getData()) {
            System.out.println(item.getCedula());
        }

        System.out.println(a.getMsg());
    }
}
