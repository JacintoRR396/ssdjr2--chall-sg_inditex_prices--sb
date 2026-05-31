package com.ssdjr2.chall.sg.inditex_prices.controller.mapper;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.MoneyResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Money;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoneyDtoMapper {

	/*
	 * DOMAIN -> DTO
	 */
	MoneyResponseDTO fromMoneyToMoneyResponseDTO(Money money);
}
