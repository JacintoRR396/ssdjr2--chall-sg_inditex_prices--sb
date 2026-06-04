package com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.ApplicationDates;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.entity.PriceEntity;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ApplicationDatesDataAccessMapper {

	/*
	 * ENTITY -> DOMAIN
	 */
	default ApplicationDates fromPriceEntityToApplicationDates(PriceEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}

		if (Objects.isNull(entity.getStartDate()) || Objects.isNull(entity.getEndDate())) {
			return null;
		}

		return ApplicationDates.builder()
				.startDate(entity.getStartDate())
				.endDate(entity.getEndDate())
				.build();
	}
}
