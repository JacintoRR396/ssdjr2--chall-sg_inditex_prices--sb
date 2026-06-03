package com.ssdjr2.chall.sg.inditex_prices.service.impl;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.mapper.PriceDtoMapper;
import com.ssdjr2.chall.sg.inditex_prices.domain.exception.PriceNotFoundException;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import com.ssdjr2.chall.sg.inditex_prices.factory.ApplicationDatesMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.BrandMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.PriceEntityMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.PriceMother;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;
import com.ssdjr2.chall.sg.inditex_prices.persistence.mapper.PriceEntityMapper;
import com.ssdjr2.chall.sg.inditex_prices.persistence.repository.PriceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

	@Mock
	private PriceDtoMapper priceDtoMapper;

	@Mock
	private PriceEntityMapper priceEntityMapper;

	@Mock
	private PriceRepository priceRepository;

	@InjectMocks
	private PriceServiceImpl service;

	@Test
	@DisplayName("Given existing brand and matching price when search then return response")
	void givenExistingBrandAndMatchingPrice_whenSearch_thenReturnResponse() {
		PriceSearchQueryDTO queryDTO = mock( PriceSearchQueryDTO.class );
		ApplicationDates dates = ApplicationDates.builder()
				.startDate( ApplicationDatesMother.test1() )
				.build();
		Price priceSearch = PriceMother.createPriceSearch( BrandMother.ZARA_ID, dates );
		PriceEntity priceEntity = PriceEntityMother.createPriceEntity();
		Price priceFound = PriceMother.createPriceFound();
		PriceSearchResponseDTO responseDTO = mock(PriceSearchResponseDTO.class);

		when(priceDtoMapper.fromPriceQueryDTOToPrice(queryDTO)).thenReturn(priceSearch);
		when(priceRepository
				.findPriceByBrandIdAndProductIdAndApplicationDate(
						BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID,	dates.getStartDate() ) )
				.thenReturn(Optional.of(priceEntity));
		when(priceEntityMapper.fromPriceEntityToPrice(priceEntity)).thenReturn(priceFound);
		when(priceDtoMapper.fromPriceToPriceSearchResponseDTO(priceFound)).thenReturn(responseDTO);

		PriceSearchResponseDTO result =	service.search(queryDTO);

		assertThat(result)
				.isNotNull()
				.isSameAs(responseDTO);

		verify(priceRepository).findPriceByBrandIdAndProductIdAndApplicationDate(
						BrandMother.ZARA_ID,	PriceMother.PRODUCT_ID_VALID, dates.getStartDate() );
	}

	@Test
	@DisplayName("Given existing brand and missing price when search then throw PriceNotFoundException")
	void givenExistingBrandAndMissingPrice_whenSearch_thenThrowPriceNotFoundException() {
		PriceSearchQueryDTO queryDTO = mock( PriceSearchQueryDTO.class );
		ApplicationDates dates = ApplicationDates.builder()
				.startDate( ApplicationDatesMother.test1() )
				.build();
		Price priceSearch = PriceMother.createPriceSearch( BrandMother.ZARA_ID, dates );

		when(priceDtoMapper.fromPriceQueryDTOToPrice(queryDTO)).thenReturn(priceSearch);
		when(priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
						BrandMother.ZARA_ID,	PriceMother.PRODUCT_ID_VALID, dates.getStartDate() ) )
				.thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.search(queryDTO))
				.isInstanceOf(PriceNotFoundException.class);
	}
}
