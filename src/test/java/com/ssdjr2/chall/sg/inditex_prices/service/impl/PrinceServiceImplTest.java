package com.ssdjr2.chall.sg.inditex_prices.service.impl;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.mapper.PriceDtoMapper;
import com.ssdjr2.chall.sg.inditex_prices.domain.exception.BrandNotFoundException;
import com.ssdjr2.chall.sg.inditex_prices.domain.exception.PriceNotFoundException;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Brand;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import com.ssdjr2.chall.sg.inditex_prices.factory.FactoryBrand;
import com.ssdjr2.chall.sg.inditex_prices.factory.FactoryDate;
import com.ssdjr2.chall.sg.inditex_prices.factory.FactoryPrice;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.BrandEntity;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;
import com.ssdjr2.chall.sg.inditex_prices.persistence.mapper.PriceEntityMapper;
import com.ssdjr2.chall.sg.inditex_prices.persistence.repository.BrandRepository;
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
class PrinceServiceImplTest {

	@Mock
	private PriceDtoMapper priceDtoMapper;

	@Mock
	private PriceEntityMapper priceEntityMapper;

	@Mock
	private PriceRepository priceRepository;

	@Mock
	private BrandRepository brandRepository;

	@InjectMocks
	private PrinceServiceImpl service;

	@Test
	@DisplayName("Given existing brand and matching price when search then return response")
	void givenExistingBrandAndMatchingPrice_whenSearch_thenReturnResponse() {
		PriceSearchQueryDTO queryDTO = mock( PriceSearchQueryDTO.class );
		Brand brand = FactoryBrand.createBrandZara();
		ApplicationDates dates = ApplicationDates.builder()
				.startDate( FactoryDate.test1() )
				.build();
		Price priceSearch = FactoryPrice.createPriceSearch( brand, dates );
		BrandEntity brandEntity = FactoryBrand.createBrandEntityZara();
		PriceEntity priceEntity = FactoryPrice.createPriceEntity();
		Price priceFound = FactoryPrice.createPriceFound( 1 );
		PriceSearchResponseDTO responseDTO = mock(PriceSearchResponseDTO.class);

		when(queryDTO.brandId()).thenReturn(1L);
		when(brandRepository.findById(1L)).thenReturn(Optional.of(brandEntity));
		when(priceDtoMapper.fromPriceQueryDTOToPrice(queryDTO))
				.thenReturn(priceSearch);
		when(priceRepository
				.findPriceByBrandIdAndProductIdAndApplicationDate(FactoryBrand.ZARA_ID, FactoryPrice.PRODUCT_ID_VALID,	dates.getStartDate()))
				.thenReturn(Optional.of(priceEntity));
		when(priceEntityMapper.fromPriceEntityToPrice(priceEntity))
				.thenReturn(priceFound);
		when(priceDtoMapper.fromPriceToPriceSearchResponseDTO(priceFound))
				.thenReturn(responseDTO);

		PriceSearchResponseDTO result =	service.search(queryDTO);

		assertThat(result).isNotNull();
		assertThat(result).isSameAs(responseDTO);

		verify(brandRepository)
				.findById(1L);
		verify(priceRepository)
				.findPriceByBrandIdAndProductIdAndApplicationDate(
						FactoryBrand.ZARA_ID,
						FactoryPrice.PRODUCT_ID_VALID,
						dates.getStartDate());
	}

	@Test
	@DisplayName("Given non existing brand when search then throw BrandNotFoundException")
	void givenNonExistingBrand_whenSearch_thenThrowBrandNotFoundException() {
		PriceSearchQueryDTO queryDTO = mock(PriceSearchQueryDTO.class);

		when(queryDTO.brandId()).thenReturn(FactoryBrand.INVALID_ID);
		when(brandRepository.findById(FactoryBrand.INVALID_ID)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.search(queryDTO))
				.isInstanceOf(BrandNotFoundException.class);

		verify(priceRepository, never())
				.findPriceByBrandIdAndProductIdAndApplicationDate( anyLong(),	anyInt(),	any());
	}

	@Test
	@DisplayName("Given existing brand and missing price when search then throw PriceNotFoundException")
	void givenExistingBrandAndMissingPrice_whenSearch_thenThrowPriceNotFoundException() {
		PriceSearchQueryDTO queryDTO = mock( PriceSearchQueryDTO.class );
		Brand brand = FactoryBrand.createBrandZara();
		ApplicationDates dates = ApplicationDates.builder()
				.startDate( FactoryDate.test1() )
				.build();
		Price priceSearch = FactoryPrice.createPriceSearch( brand, dates );
		BrandEntity brandEntity = FactoryBrand.createBrandEntityZara();

		when(queryDTO.brandId()).thenReturn(FactoryBrand.ZARA_ID);
		when(brandRepository.findById(FactoryBrand.ZARA_ID))
				.thenReturn(Optional.of(brandEntity));
		when(priceDtoMapper.fromPriceQueryDTOToPrice(queryDTO))
				.thenReturn(priceSearch);
		when(priceRepository
				.findPriceByBrandIdAndProductIdAndApplicationDate(
						FactoryBrand.ZARA_ID,
						FactoryPrice.PRODUCT_ID_VALID,
						dates.getStartDate()))
				.thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.search(queryDTO))
				.isInstanceOf(PriceNotFoundException.class);
	}
}
