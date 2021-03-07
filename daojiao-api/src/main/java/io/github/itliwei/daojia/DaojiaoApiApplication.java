package io.github.itliwei.daojia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"io.github.itliwei.daojia","io.github.itliwei.vboot"})
public class DaojiaoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaojiaoApiApplication.class, args);
	}

}
