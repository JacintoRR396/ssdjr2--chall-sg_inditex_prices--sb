package com.ssdjr2.chall.sg.inditex_prices.domain.mappers;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.repositories.entities.PriceEntity;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ApplicationDatesMapper {

	/*
	 * ENTITY -> DOMAIN
	 */
	default ApplicationDates fromPriceEntityToApplicationDates(PriceEntity entity) {

		if (Objects.isNull(entity.getStartDate()) && Objects.isNull(entity.getEndDate())) {
			return null;
		}

		return ApplicationDates.builder()
				.startDate(entity.getStartDate())
				.endDate(entity.getEndDate())
				.build();
	}
}
