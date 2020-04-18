package com.lesson.warehouse;

import com.lesson.warehouse.domain.Product;
import com.lesson.warehouse.repository.WarehouseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.lesson.warehouse")
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

	//initial data
	@Bean
	public CommandLineRunner dataLoader(WarehouseRepository repository) {
		return args -> {
			repository.save(new Product(1, "phone", 999, 50));
			repository.save(new Product(2, "TV", 699, 1000));
			repository.save(new Product(3, "lamp", 25, 400));
			repository.save(new Product(3, "computer", 2500, 25));
			repository.save(new Product(3, "bike", 145, 100));
		};
	}
}
