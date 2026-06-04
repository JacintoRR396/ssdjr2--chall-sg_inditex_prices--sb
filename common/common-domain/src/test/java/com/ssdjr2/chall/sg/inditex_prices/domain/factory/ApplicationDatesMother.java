package com.ssdjr2.chall.sg.inditex_prices.domain.factory;

import java.time.LocalDateTime;

public class ApplicationDatesMother {

	private ApplicationDatesMother() {
		throw new UnsupportedOperationException();
	}

	public static LocalDateTime test1() {
		return LocalDateTime.of( 2020, 6, 14, 10, 0 );
	}

	public static LocalDateTime test2() {
		return LocalDateTime.of( 2020, 6, 14, 16, 0 );
	}
}
