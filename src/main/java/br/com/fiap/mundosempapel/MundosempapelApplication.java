package br.com.fiap.mundosempapel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MundosempapelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MundosempapelApplication.class, args);
		
	}

}
