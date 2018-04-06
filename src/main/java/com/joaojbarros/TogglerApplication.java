package com.joaojbarros;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.joaojbarros.model.Toggles;
import com.joaojbarros.repository.TogglesRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = TogglesRepository.class)
public class TogglerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogglerApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(TogglesRepository togglesRepository) {

        return args -> {

            Toggles obj = togglesRepository.findCustomByServiceId("serviceABC");
            System.out.println(obj);

            Toggles obj2 = togglesRepository.findCustomByServiceIdVersion("serviceABC", "1.0");
            System.out.println(obj2);
        };

    }
}
