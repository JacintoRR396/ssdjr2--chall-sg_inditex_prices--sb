package com.ssdjr2.chall.sg.inditex_prices.persistence.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ApplicationDatesEntityMapper {

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
