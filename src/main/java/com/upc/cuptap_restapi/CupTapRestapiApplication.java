package com.upc.cuptap_restapi;

import com.upc.cuptap_restapi.Services.Utils.Estadisticas;
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

public class CupTapRestapiApplication implements CommandLineRunner {


    private final Estadisticas estadisticas;

    public CupTapRestapiApplication(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    public static void main(String[] args) {

        SpringApplication.run(CupTapRestapiApplication.class, args);
    }

    @Bean
    public GroupedOpenApi CupTapAPI(@Value("${spring.application.version}") String appVersion, @Value("${spring.application.name}") String Title) {
        String[] paths = { "/CupTapAPI/**" };
        return GroupedOpenApi.builder().
                group("CupTapAPI")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title(Title).version(appVersion)))
                .pathsToMatch(paths)
                .build();
    }


    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Estadisticas.getInstance().TotalRecaudado() = " + estadisticas.totalRecaudado());
//        System.out.println("Estadisticas.getInstance().CantPedidos() = " + estadisticas.cantPedidos());
    }
}
