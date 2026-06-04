package com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory;

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

	public static LocalDateTime test3() {
		return LocalDateTime.of( 2020, 6, 14, 21, 0 );
	}

	public static LocalDateTime test4() {
		return LocalDateTime.of( 2020, 6, 15, 10, 0 );
	}

	public static LocalDateTime test5() {
		return LocalDateTime.of( 2020, 6, 16, 21, 0 );
	}

	public static LocalDateTime testInvalid() {
		return LocalDateTime.of(2026, 6, 1, 10, 0);
	}
}
