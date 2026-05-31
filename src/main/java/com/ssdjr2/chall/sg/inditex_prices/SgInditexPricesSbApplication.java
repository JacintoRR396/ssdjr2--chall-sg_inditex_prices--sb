package com.ssdjr2.chall.sg.inditex_prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SgInditexPricesSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgInditexPricesSbApplication.class, args);
	}
}
