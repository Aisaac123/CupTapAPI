package com.upc.cuptap_restapi;

import com.upc.cuptap_restapi.Models.Entities.Usuario;
import com.upc.cuptap_restapi.Repositories.DAO.UsuarioDAO;
import com.upc.cuptap_restapi.Services.Logic.Estadisticas;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")

public class CupTapRestapiApplication {



    public CupTapRestapiApplication() {
    }

    public static void main(String[] args) {

        SpringApplication.run(CupTapRestapiApplication.class, args);
    }

    @Bean
    public GroupedOpenApi CupTapAPI(@Value("${spring.application.version}") String appVersion, @Value("${spring.application.name}") String Title) {
        String[] paths = {"/v1/**"};
        return GroupedOpenApi.builder().
                group("CupTapAPI")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title(Title).version(appVersion)))
                .pathsToMatch(paths)
                .build();
    }

}
