package varadraj.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket API_Documentatin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("varadraj"))
				.paths(PathSelectors.regex("/.*"))
				.build()
				.apiInfo(customAPIDetails());
	}

	private ApiInfo customAPIDetails() {
		ApiInfo apiInfo = new ApiInfo("Paridhan Backend", "poweredBy Spring Boot", "1.0", "TOS"
				, new Contact("VRaj23", "https://github.com/VRaj23/Paridhan-Backend", "")
				, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
		
		return apiInfo;
	}
}


