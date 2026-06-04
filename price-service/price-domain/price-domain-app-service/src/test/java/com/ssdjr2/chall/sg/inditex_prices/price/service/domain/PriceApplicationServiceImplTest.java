package com.ssdjr2.chall.sg.inditex_prices.price.service.domain;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.exception.PriceNotFoundException;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory.*;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.mapper.PriceDtoMapper;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.ports.output.repository.PriceRepository;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.service.PriceDomainService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceApplicationServiceImplTest {

	@Mock
	private PriceDomainService priceDomainService;

	@Mock
	private PriceDtoMapper priceDtoMapper;

	@Mock
	private PriceRepository priceRepository;

	@InjectMocks
	private PriceApplicationServiceImpl priceApplicationService;

	@Test
	@DisplayName("GIVEN a valid search query WHEN searchPrice is called THEN returns a valid response")
	void givenValidQuery_whenSearchPrice_thenReturnResponse() {
		PriceSearchQueryDTO queryDTO = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest1();
		ApplicationDates dates = ApplicationDates.builder()
				.startDate( ApplicationDatesMother.test1() )
				.endDate( ApplicationDatesMother.test1() )
				.build();
		Price priceSearch = PriceMother.createPriceSearch( BrandMother.ZARA_ID, dates );
		PriceSearchResponseDTO responseDTO = PriceSearchResponseDTOMother.createPriceSearchResponseDTOAboutTest1AndTest3();

		when(priceDtoMapper.fromPriceQueryDTOToPrice(queryDTO)).thenReturn(priceSearch);
		doNothing().when(priceDomainService).validateAndInitiatePrice(priceSearch);
		when(priceRepository.findById(priceSearch)).thenReturn(Optional.of(priceSearch));
		when(priceDtoMapper.fromPriceToPriceSearchResponseDTO(priceSearch)).thenReturn(responseDTO);

		PriceSearchResponseDTO result = priceApplicationService.searchPrice(queryDTO);

		assertNotNull(result);
		verify(priceDtoMapper).fromPriceQueryDTOToPrice(queryDTO);
		verify(priceDomainService).validateAndInitiatePrice(priceSearch);
		verify(priceRepository).findById(priceSearch);
		verify(priceDtoMapper).fromPriceToPriceSearchResponseDTO(priceSearch);
	}

	@Test
	@DisplayName("GIVEN a query for a non-existing price WHEN searchPrice is called THEN throw PriceNotFoundException")
	void givenNonExistingPrice_whenSearchPrice_thenThrowException() {
		PriceSearchQueryDTO queryDTO = Mockito.mock(PriceSearchQueryDTO.class);
		Price priceSearch = PriceMother.createPriceMapping();

		when(priceDtoMapper.fromPriceQueryDTOToPrice(queryDTO)).thenReturn(priceSearch);
		doNothing().when(priceDomainService).validateAndInitiatePrice(priceSearch);
		when(priceRepository.findById(priceSearch)).thenReturn(Optional.empty());

		assertThrows(PriceNotFoundException.class, () -> {
			priceApplicationService.searchPrice(queryDTO);
		});

		verify(priceDtoMapper).fromPriceQueryDTOToPrice(queryDTO);
		verify(priceDomainService).validateAndInitiatePrice(priceSearch);
	}
}
