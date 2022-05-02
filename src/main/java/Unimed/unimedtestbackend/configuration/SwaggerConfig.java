package Unimed.unimedtestbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	
	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Test Unimed")
						.description("Test de backend para o processo seletivo da Unimed")
						.version("v0.0.1")
					.contact(new Contact()
						.name("Pedro Marcos")
						.url("https://github.com/Pedro-Marcos1223")
						.email("pedromarcospdm@gmail.com")))
					.externalDocs(new ExternalDocumentation()
						.description("Github")
						.url("https://github.com/Pedro-Marcos1223/Unimed_test"));
	}
	
}
