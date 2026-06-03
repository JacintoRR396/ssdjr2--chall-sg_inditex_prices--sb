package com.ssdjr2.chall.sg.inditex_prices.persistence.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Money;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MoneyEntityMapper {

	/*
	 * ENTITY -> DOMAIN
	 */
	default Money fromPriceEntityToMoney(PriceEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}

		if ( Objects.isNull(entity.getPrice()) && Objects.isNull(entity.getCurrency()) ) {
			return null;
		}

		return Money.builder()
				.price(Money.setScaleToPrice(entity.getPrice()))
				.currency(entity.getCurrency())
				.build();
	}
}
