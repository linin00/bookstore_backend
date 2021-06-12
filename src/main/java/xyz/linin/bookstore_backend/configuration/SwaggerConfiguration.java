package xyz.linin.bookstore_backend.configuration;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import xyz.linin.bookstore_backend.constants.SecurityConstants;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Configuration
public class SwaggerConfiguration {
    private final TypeResolver typeResolver;

    public SwaggerConfiguration(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    public Docket createRestApi() {
        // https://springfox.github.io/springfox/docs/current/#springfox-spring-mvc-and-spring-boot
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.xyz.linin"))
                .paths(PathSelectors.any())
                .build()
//                .genericModelSubstitutes(DataResponse.class, DataResponseBody.class)
//                .additionalModels(typeResolver.resolve(DataResponse.class))
//                .directModelSubstitute(DataResponse.class, DataResponseBody.class)
//                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(DataResponse.class, WildcardType.class), typeResolver.resolve(DataResponseBody.class, WildcardType.class)))
                .globalResponses(HttpMethod.POST, globalResponses())
                .globalResponses(HttpMethod.PUT, globalResponses())
                .consumes(new HashSet<>(Collections.singleton(MediaType.APPLICATION_JSON_VALUE)))
                .produces(new HashSet<>(Collections.singleton(MediaType.APPLICATION_JSON_VALUE)))
                .securityContexts(securityContext())
                .securitySchemes(securitySchemes());
    }

    // Display authorization options in Swagger UI
    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey("JWT", SecurityConstants.TOKEN_HEADER, "header"));
    }

    // Add authorization context when trying API in Swagger UI
    private List<SecurityContext> securityContext() {
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
        return Collections.singletonList(securityContext);
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }

    private List<Response> globalResponses() {
        return Collections.singletonList(new ResponseBuilder()
                .code("400")
                .description("Bad Request")
                .build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("bookstore")
                .description("大聪明书店 后端")
                .version("1.0")
                .build();
    }
}
