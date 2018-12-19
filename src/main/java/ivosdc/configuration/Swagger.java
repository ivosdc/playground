package ivosdc.configuration;

import com.google.common.base.Predicates;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger {
    private final String applicationUri;

    Swagger(@Value("${vcap.application.application_uris[0]}") String applicationUri) {
        this.applicationUri = applicationUri;
    }

    @Bean
    public Docket getSwaggerDocket() throws IOException {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.or(PathSelectors.ant("/api/**")
                ))
                .build()
                .tags(new Tag("Demos", "Playground, tests"))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() throws IOException {
        InputStream apiDescriptionInputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("api/description.md");
        String description = IOUtils.toString(apiDescriptionInputStream, Charset.defaultCharset());

        return new ApiInfo(
                "Demo API Documentation",
                description,
                "1.0.0",
                null,
                new Contact("Ivo", "", ""),
                null,
                null,
                Collections.singletonList(new ObjectVendorExtension("x-logo")));
    }

}