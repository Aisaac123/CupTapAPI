package com.upc.cuptap_restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CupTapRestapiApplication {

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("Argumento: " + arg);
        }
        SpringApplication.run(CupTapRestapiApplication.class, args);

    }
}
