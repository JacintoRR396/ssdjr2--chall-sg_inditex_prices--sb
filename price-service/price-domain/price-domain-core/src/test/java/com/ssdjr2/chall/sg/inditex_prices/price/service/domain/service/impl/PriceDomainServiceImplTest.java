package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.service.impl;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceDomainServiceImplTest {

	@Mock
	private Price price;

	@InjectMocks
	private PriceDomainServiceImpl priceDomainService;

	@Test
	@DisplayName("GIVEN a valid price WHEN validateAndInitiatePrice THEN price is initialized")
	void givenValidPrice_whenValidateAndInitiatePrice_thenSuccess() {
		doNothing().when(price).initPrice();

		priceDomainService.validateAndInitiatePrice(price);

		verify(price, times(1)).initPrice();
	}
}
