package tech.tomberg.tombergapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TombergApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TombergApiApplication.class, args);
	}

}
