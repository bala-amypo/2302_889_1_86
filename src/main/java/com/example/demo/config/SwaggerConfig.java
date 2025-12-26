// // package com.example.demo.config;

// // import io.swagger.v3.oas.models.OpenAPI;
// // import io.swagger.v3.oas.models.servers.Server;
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import java.util.List;

// // @Configuration
// // public class SwaggerConfig {

// //     @Bean
// //     public OpenAPI customOpenAPI() {
// //         return new OpenAPI()
// //                 // You need to change the port as per your server
// //                 .servers(List.of(
// //                         new Server().url("https://9247.pro604cr.amypo.ai/")
// //                 ));
// //         }
// // }
// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.List;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI api() {  // Keep this method for your tests
//         return new OpenAPI()
//                 .servers(List.of(
//                         new Server().url("https://9247.pro604cr.amypo.ai/")
//                 ))
//                 .info(new io.swagger.v3.oas.models.info.Info()
//                         .title("Demo API")
//                         .version("1.0")
//                         .description("Farm Management API"));
//     }
// }







// // src/main/java/com/example/demo/config/SwaggerConfig.java
// package com.example.demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @Configuration
// @EnableSwagger2
// public class SwaggerConfig {

//     @Bean
//     public Docket api() {
//         return new Docket(DocumentationType.SWAGGER_2)
//                 .select()
//                 .apis(RequestHandlerSelectors.any())
//                 .paths(PathSelectors.any())
//                 .build();
//     }
// }
package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        final String securitySchemeName = "JWT";

        return new OpenAPI()
                .info(new Info()
                        .title("Crop & Fertilizer Suggestion API")
                        .version("1.0.0")
                        .description("REST API for providing intelligent crop and fertilizer suggestions based on farm conditions")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("you@example.com")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
