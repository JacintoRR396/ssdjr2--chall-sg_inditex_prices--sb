package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory;

import java.time.LocalDateTime;

public class ApplicationDatesMother {

	private ApplicationDatesMother() {
		throw new UnsupportedOperationException();
	}

	public static LocalDateTime test1() {
		return LocalDateTime.of( 2020, 6, 14, 10, 0 );
	}
}
