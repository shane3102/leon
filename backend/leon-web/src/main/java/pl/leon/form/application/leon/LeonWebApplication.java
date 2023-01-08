package pl.leon.form.application.leon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LeonWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeonWebApplication.class, args);
	}

}
