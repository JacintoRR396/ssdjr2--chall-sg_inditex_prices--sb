package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.exception;

import com.ssdjr2.chall.sg.inditex_prices.domain.exception.DomainException;

public class PriceNotFoundException extends DomainException {

	public PriceNotFoundException(String message) {
		super(message);
	}

	public PriceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PriceNotFoundException( Long brandId, Integer productId ) {
		super(String.format("Price not found for brand id %d and product id %d", brandId,	productId));
	}
}
