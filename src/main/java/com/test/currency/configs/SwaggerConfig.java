package com.test.currency.configs;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Currencies exchange")
                                .version("1.0.0")
                                .description("Testing example")
                                .contact(new Contact()
                                        .name("Oleg P")
                                        .email("iGarik10@yandex.ru"))
                );
    }

}
