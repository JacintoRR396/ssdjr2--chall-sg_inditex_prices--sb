package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.ApplicationDatesResponseDTO;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ApplicationDatesDtoMapper {

	/*
	 * DTO -> DOMAIN
	 */
	default ApplicationDates fromApplicationDateToApplicationDates(LocalDateTime applicationDate) {

		if( Objects.isNull(applicationDate) ) {
			return null;
		}

		return ApplicationDates.builder()
				.startDate(applicationDate)
				.endDate(applicationDate)
				.build();
	}

	/*
	 * DOMAIN -> DTO
	 */
	ApplicationDatesResponseDTO fromApplicationDatesToApplicationDatesResponseDTO(ApplicationDates applicationDates);
}
