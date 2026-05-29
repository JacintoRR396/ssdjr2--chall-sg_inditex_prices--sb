package com.ssdjr2.chall.sg.inditex_prices.domain.mappers;

import com.ssdjr2.chall.sg.inditex_prices.controllers.dtos.response.MoneyResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Money;
import com.ssdjr2.chall.sg.inditex_prices.repositories.entities.PriceEntity;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MoneyMapper {

	/*
	 * ENTITY -> DOMAIN
	 */
	default Money fromPriceEntityToMoney(PriceEntity entity) {

		if ( Objects.isNull(entity.getPrice()) && Objects.isNull(entity.getCurrency()) ) {
			return null;
		}

		return Money.builder()
				.price(entity.getPrice())
				.currency(entity.getCurrency())
				.build();
	}

	/*
	 * DOMAIN -> DTO
	 */
	MoneyResponseDTO fromMoneyToMoneyResponseDTO(Money money);
}
