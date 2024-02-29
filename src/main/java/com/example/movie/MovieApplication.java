package com.example.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@SpringBootApplication
public class MovieApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieApplication.class, args);
	}

	@Bean
	public TextEncryptor textEncryptor() {


		return Encryptors.text("64696e676f", "73616c7479");
	}

}
