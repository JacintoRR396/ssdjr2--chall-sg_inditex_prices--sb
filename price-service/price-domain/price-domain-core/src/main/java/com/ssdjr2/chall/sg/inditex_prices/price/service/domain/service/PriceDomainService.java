package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.service;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;

public interface PriceDomainService {

	void validateAndInitiatePrice(Price price);
}
