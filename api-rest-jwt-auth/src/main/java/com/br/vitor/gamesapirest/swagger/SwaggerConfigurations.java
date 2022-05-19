package com.br.vitor.gamesapirest.swagger;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {
	
	@Bean
	public Docket forumApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.br.vitor.gamesapirest"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo())
				.globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                    .name("Authorization")
                                    .description("Header para Token JWT")
                                    .modelRef(new ModelRef("string"))
                                    .parameterType("header")
                                    .required(false)
                                    .build()));
		}

	
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Games API REST",
				"API REST de games e categorias",
				"1.0",
				"Terms of Service",
				new Contact("Vitor Souza", "https://github.com/vsouzx", "vtsoliveira2001@gmail.com"),
				"Apache License Version 2.0",
				"https://www.apache.org/licensen.html", new ArrayList<VendorExtension>()
				);
				
		return apiInfo;
	}

}
