package cz.centrum.haffner.SimpleTrainingDavid.REST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan ("cz.centrum.haffner.SimpleTrainingDavid")
public class SimpleTrainingDavidApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleTrainingDavidApplication.class, args);
	}

}

