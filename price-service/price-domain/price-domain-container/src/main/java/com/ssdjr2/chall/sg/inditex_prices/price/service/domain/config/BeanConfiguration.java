package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.config;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.service.PriceDomainService;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.service.impl.PriceDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	@Bean
	public PriceDomainService priceDomainService() {
		return new PriceDomainServiceImpl();
	}
}
