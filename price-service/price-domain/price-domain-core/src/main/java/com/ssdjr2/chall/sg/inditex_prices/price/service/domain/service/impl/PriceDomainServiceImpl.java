package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.service.impl;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.service.PriceDomainService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PriceDomainServiceImpl implements PriceDomainService {

	@Override
	public void validateAndInitiatePrice(Price price) {
		price.initPrice();

		LOGGER.info("Price with id: {} is initiated", price.getId().getId());
	}
}
