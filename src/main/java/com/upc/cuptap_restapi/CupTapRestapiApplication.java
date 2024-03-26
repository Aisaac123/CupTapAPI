package com.upc.cuptap_restapi;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CupTapRestapiApplication {

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("Argumento: " + arg);
        }
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
}
