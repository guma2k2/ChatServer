package com.chatapp.server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApiConfig () {
        return new OpenAPI()
                .info(new Info()
                        .description("Api for telegram app")
                        .contact(new Contact().email("thuanngo3072002@gmail.com").name("name"))
                        .license(new License().name("Apache 2.0"))
                        .version("1.0.0"))
                ;
    }
}
