package com.example.jwbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class JwbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwbookApplication.class, args);
	}

}
