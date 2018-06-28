package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.cxf.TestConfig;

@SpringBootApplication
@Import(TestConfig.class)
public class WebserviceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceDemoApplication.class, args);
	}
}
