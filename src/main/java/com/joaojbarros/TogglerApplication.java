package com.joaojbarros;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.joaojbarros.model.ServiceToggle;
import com.joaojbarros.repository.TogglerRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = TogglerRepository.class)
public class TogglerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogglerApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(TogglerRepository togglesRepository) {

        return args -> {

            ServiceToggle obj = togglesRepository.findCustomByServiceId("serviceABC");
            System.out.println(obj);

            ServiceToggle obj2 = togglesRepository.findCustomByServiceIdVersion("serviceABC", "1.0");
            System.out.println(obj2);
        };

    }
}
