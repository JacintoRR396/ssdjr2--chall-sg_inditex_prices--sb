package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.exception;

import com.ssdjr2.chall.sg.inditex_prices.domain.exception.DomainException;

public class PriceDomainException extends DomainException {

	public PriceDomainException(String message) {
		super(message);
	}

	public PriceDomainException(String message, Throwable cause) {
		super(message, cause);
	}
}
