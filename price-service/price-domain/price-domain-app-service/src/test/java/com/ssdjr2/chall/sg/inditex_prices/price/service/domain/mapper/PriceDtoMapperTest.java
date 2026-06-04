package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.Money;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceDtoMapperTest {

	private PriceDtoMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = Mappers.getMapper(PriceDtoMapper.class);

		ApplicationDatesDtoMapper datesMapper = Mappers.getMapper(ApplicationDatesDtoMapper.class);
		MoneyDtoMapper moneyMapper = Mappers.getMapper(MoneyDtoMapper.class);

		ReflectionTestUtils.setField(mapper, "applicationDatesDtoMapper", datesMapper);
		ReflectionTestUtils.setField(mapper, "moneyDtoMapper", moneyMapper);
	}

	@Test
	@DisplayName("Map PriceSearchQueryDTO to Price Domain entity successfully")
	void givenValidPriceSearchQueryDTO_whenMappingToPrice_thenReturnsPopulatedPriceDomain() {
		LocalDateTime targetDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
		PriceSearchQueryDTO dto = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest1();

		Price result = mapper.fromPriceQueryDTOToPrice(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNull();
		assertThat(result.getBrandId()).isNotNull();
		assertThat(result.getBrandId().getId()).isEqualTo(BrandMother.ZARA_ID);
		assertThat(result.getProductId()).isNotNull();
		assertThat(result.getProductId().getId()).isEqualTo(PriceMother.PRODUCT_ID_VALID);
		assertThat(result.getPriceList()).isNull();
		assertThat(result.getPriority()).isNull();
		assertThat(result.getApplicationDates()).isNotNull();
		assertThat(result.getApplicationDates().startDate()).isEqualTo(targetDate);
		assertThat(result.getApplicationDates().endDate()).isEqualTo(targetDate);
		assertThat(result.getMoney()).isNull();
	}

	@Test
	@DisplayName("Map Price Domain entity to PriceSearchResponseDTO with flattened fields successfully")
	void givenPopulatedPriceDomain_whenMappingToPriceSearchResponseDTO_thenReturnsMatchingDTO() {
		Price domainPrice = PriceMother.createPriceMapping();

		PriceSearchResponseDTO result = mapper.fromPriceToPriceSearchResponseDTO(domainPrice);

		assertThat(result).isNotNull();
		assertThat(result.brandId()).isEqualTo(BrandMother.ZARA_ID);
		assertThat(result.productId()).isEqualTo(PriceMother.PRODUCT_ID_VALID);
		assertThat(result.priceList()).isEqualTo(PriceMother.PRICE_LIST_TARIFF1);
		assertThat(result.applicationDates()).isNotNull();
		assertThat(result.applicationDates().startDate()).isEqualTo(ApplicationDatesMother.test1());
		assertThat(result.applicationDates().endDate()).isEqualTo(ApplicationDatesMother.test1());
		assertThat(result.money()).isNotNull();
		assertThat(result.money().price()).isEqualTo(
				Money.setScaleToPrice(BigDecimal.valueOf(MoneyMother.TARIFF1_PRICE)));
		assertThat(result.money().currency()).isEqualTo(MoneyMother.CURRENCY);
	}

	@Test
	@DisplayName("Return null when input DTO or Domain entity is null")
	void givenNullInputs_whenMappingMethodsAreCalled_thenReturnsNull() {
		Price priceResult = mapper.fromPriceQueryDTOToPrice(null);
		PriceSearchResponseDTO dtoResult = mapper.fromPriceToPriceSearchResponseDTO(null);

		assertThat(priceResult).isNull();
		assertThat(dtoResult).isNull();
	}

	@Test
	@DisplayName("Map incomplete Price Domain to Response DTO without throwing exceptions")
	void givenPriceDomainWithNullAggregates_whenMappingToResponseDTO_thenReturnsDTOWithNullFields() {
		Price incompletePrice = PriceMother.createPriceMappingIncomplete();

		PriceSearchResponseDTO result = mapper.fromPriceToPriceSearchResponseDTO(incompletePrice);

		assertThat(result).isNotNull();
		assertThat(result.applicationDates()).isNull();
		assertThat(result.money()).isNull();
	}
}
