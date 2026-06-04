package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.service.impl;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.PriceId;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.exception.PriceDomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
		PriceId mockId = new PriceId(1L);
		when(price.getId()).thenReturn(mockId);

		priceDomainService.validateAndInitiatePrice(price);

		verify(price, times(1)).initPrice();
	}

	@Test
	@DisplayName("GIVEN an invalid price WHEN validateAndInitiatePrice THEN propagates exception")
	void givenInvalidPrice_whenValidateAndInitiatePrice_thenPropagatesException() {
		doThrow(new PriceDomainException("error.price.invalid"))
				.when(price).initPrice();

		assertThrows(PriceDomainException.class, () ->
				priceDomainService.validateAndInitiatePrice(price)
		);

		verify(price, times(1)).initPrice();
	}
}
