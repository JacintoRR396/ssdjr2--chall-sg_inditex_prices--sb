package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.ApplicationDatesResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationDatesDtoMapperTest {

	private final ApplicationDatesDtoMapper mapper = Mappers.getMapper(ApplicationDatesDtoMapper.class);

	@Test
	@DisplayName("Map LocalDateTime to Domain Entity successfully")
	void givenValidLocalDateTime_whenMappingToApplicationDates_thenReturnsPopulatedDomain() {
		LocalDateTime now = LocalDateTime.now();

		ApplicationDates result = mapper.fromApplicationDateToApplicationDates(now);

		assertThat(result).isNotNull();
		assertThat(result.startDate()).isEqualTo(now);
		assertThat(result.endDate()).isEqualTo(now);
	}

	@Test
	@DisplayName("Return null Domain Entity when input date is null")
	void givenNullLocalDateTime_whenMappingToApplicationDates_thenReturnsNull() {
		ApplicationDates result = mapper.fromApplicationDateToApplicationDates(null);

		assertThat(result).isNull();
	}

	@Test
	@DisplayName("Map Domain Entity to Response DTO successfully")
	void givenPopulatedApplicationDates_whenMappingToResponseDTO_thenReturnsMatchingDTO() {
		LocalDateTime now = LocalDateTime.now();
		ApplicationDates domain = ApplicationDates.builder()
				.startDate(now)
				.endDate(now)
				.build();

		ApplicationDatesResponseDTO result = mapper.fromApplicationDatesToApplicationDatesResponseDTO(domain);

		assertThat(result).isNotNull();
		assertThat(result.startDate()).isEqualTo(now);
		assertThat(result.endDate()).isEqualTo(now);
	}

	@Test
	@DisplayName("Return null Response DTO when input Domain Entity is null")
	void givenNullApplicationDatesDomain_whenMappingToResponseDTO_thenReturnsNull() {
		ApplicationDatesResponseDTO result = mapper.fromApplicationDatesToApplicationDatesResponseDTO(null);

		assertThat(result).isNull();
	}
}
