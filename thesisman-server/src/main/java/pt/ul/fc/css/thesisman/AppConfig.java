package pt.ul.fc.css.thesisman;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "pt.ul.fc.css.thesisman")
@EnableJpaRepositories(basePackages = "pt.ul.fc.css.thesisman.repositories")
public class AppConfig {
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
