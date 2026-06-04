package com.ssdjr2.chall.sg.inditex_prices.price.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.ssdjr2.chall.sg.inditex_prices.price.service" })
@EntityScan(basePackages = { "com.ssdjr2.chall.sg.inditex_prices.price.service" })
@SpringBootApplication(scanBasePackages = "com.ssdjr2.chall.sg.inditex_prices")
public class PriceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceServiceApplication.class, args);
	}
}
