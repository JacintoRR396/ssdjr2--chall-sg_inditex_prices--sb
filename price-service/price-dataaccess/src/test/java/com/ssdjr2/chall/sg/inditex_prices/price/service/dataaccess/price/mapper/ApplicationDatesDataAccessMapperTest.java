package com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.entity.PriceEntity;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.factory.PriceEntityMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationDatesDataAccessMapperTest {

	private final ApplicationDatesDataAccessMapper mapper = Mappers.getMapper(ApplicationDatesDataAccessMapper.class);

	@Test
	@DisplayName("Return null Domain entity when PriceEntity input is null")
	void givenNullPriceEntity_whenMappingToApplicationDates_thenReturnsNull() {
		ApplicationDates result = mapper.fromPriceEntityToApplicationDates(null);

		assertThat(result).isNull();
	}

	@Test
	@DisplayName("Return null Domain entity when both dates are null in PriceEntity")
	void givenPriceEntityWithBothDatesNull_whenMappingToApplicationDates_thenReturnsNull() {
		PriceEntity entity = PriceEntityMother.createPriceEntityWithDataNull();

		ApplicationDates result = mapper.fromPriceEntityToApplicationDates(entity);

		assertThat(result).isNull();
	}

	@Test
	@DisplayName("Map successfully when only endDate is null in PriceEntity")
	void givenPriceEntityWithNullEndDateOnly_whenMappingToApplicationDates_thenReturnsDomainWithNullEndDate() {
		PriceEntity entity = PriceEntityMother.createPriceEntityWithOnlyFirstDataNull();

		ApplicationDates result = mapper.fromPriceEntityToApplicationDates(entity);

		assertThat(result).isNull();
	}

	@Test
	@DisplayName("Map PriceEntity dates to ApplicationDates Domain entity successfully")
	void givenPriceEntityWithValidDates_whenMappingToApplicationDates_thenReturnsPopulatedDomain() {
		PriceEntity entity = PriceEntityMother.createPriceEntity();

		ApplicationDates result = mapper.fromPriceEntityToApplicationDates(entity);

		assertThat(result).isNotNull();
		assertThat(result.startDate()).isEqualTo(PriceEntityMother.START_DATE_TARIFF1);
		assertThat(result.endDate()).isEqualTo(PriceEntityMother.END_DATE_TARIFF1);
	}
}
