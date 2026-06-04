package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.Money;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.MoneyResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory.MoneyMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyDtoMapperTest {

	private final MoneyDtoMapper mapper = Mappers.getMapper(MoneyDtoMapper.class);

	@Test
	@DisplayName("Map Money Domain entity to Response DTO successfully")
	void givenValidMoneyDomain_whenMappingToMoneyResponseDTO_thenReturnsPopulatedDTO() {
		Money domainMoney = MoneyMother.createMoneyTariff1();

		MoneyResponseDTO result = mapper.fromMoneyToMoneyResponseDTO(domainMoney);

		assertThat(result).isNotNull();
		assertThat(result.price()).isEqualTo(
				Money.setScaleToPrice(BigDecimal.valueOf(MoneyMother.TARIFF1_PRICE)));
		assertThat(result.currency()).isEqualTo(MoneyMother.CURRENCY);
	}

	@Test
	@DisplayName("Return null Money Response DTO when input Domain entity is null")
	void givenNullMoneyDomain_whenMappingToMoneyResponseDTO_thenReturnsNull() {
		MoneyResponseDTO result = mapper.fromMoneyToMoneyResponseDTO(null);

		assertThat(result).isNull();
	}
}
