package com.ssdjr2.chall.sg.inditex_prices.domain.exception;

public class PriceNotFoundException extends RuntimeException {

	public PriceNotFoundException(Long brandId, Integer productId) {
		super(String.format("Price not found for brand %d and product %d", brandId,	productId));
	}
}
