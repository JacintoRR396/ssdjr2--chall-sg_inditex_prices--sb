package com.ssdjr2.chall.sg.inditex_prices.domain.exception;

public class BrandNotFoundException extends RuntimeException {

	public BrandNotFoundException( Long brandId ) {
		super(String.format("Brand not found for brand id %d", brandId));
	}
}
