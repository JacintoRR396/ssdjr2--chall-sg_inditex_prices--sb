package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.Money;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.MoneyResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoneyDtoMapper {

	/*
	 * DOMAIN -> DTO
	 */
	MoneyResponseDTO fromMoneyToMoneyResponseDTO(Money money);
}
