package com.ssdjr2.chall.sg.inditex_prices.domain.exception;

public class PriceNotFoundException extends RuntimeException {

	public PriceNotFoundException( Long brandId, Integer productId ) {
		super(String.format("Price not found for brand id %d and product id %d", brandId,	productId));
	}
}
