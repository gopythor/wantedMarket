package com.example.wantedmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@EnableJpaAuditing
@EnableJpaRepositories
@SpringBootApplication
public class WantedMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(WantedMarketApplication.class, args);
	}

}
