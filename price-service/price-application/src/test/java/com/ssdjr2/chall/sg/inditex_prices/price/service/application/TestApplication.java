package com.ssdjr2.chall.sg.inditex_prices.price.service.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.ssdjr2.chall.sg.inditex_prices")
@EnableJpaRepositories(basePackages = "com.ssdjr2.chall.sg.inditex_prices")
@SpringBootApplication(scanBasePackages = "com.ssdjr2.chall.sg.inditex_prices")
public class TestApplication {
	// Intentionally empty class so that @WebMvcTest can find an anchor point
}

