package br.com.filmes.starwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class GerenciadorPlanetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPlanetasApplication.class, args);
	}
}
