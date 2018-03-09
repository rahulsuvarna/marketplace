package com.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MarketPlaceApplication {
    
    public static void main(String args[]) {
        SpringApplication.run(MarketPlaceApplication.class, args);
    }
 
    /**
     * Swagger configuration
     * @return
     */
    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Market Place")
                .select()
                .apis(basePackage("com.marketplace.offer.controller"))
                // .paths(regex("/merchants/"))
                // .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }
}
