package com.ssdjr2.chall.sg.inditex_prices.controller.mapper;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ApplicationDatesDtoMapper.class, MoneyDtoMapper.class})
public interface PriceDtoMapper {

	/*
	 * DTO -> DOMAIN
	 */
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "brandId", target = "brandId")
	@Mapping(source = "productId", target = "productId")
	@Mapping(target = "priceList", ignore = true)
	@Mapping(target = "priority", ignore = true)
	@Mapping(source = "applicationDate", target = "applicationDates")
	@Mapping(target = "money", ignore = true)
	Price fromPriceQueryDTOToPrice(PriceSearchQueryDTO dto);

	/*
	 * DOMAIN -> DTO
	 */
	PriceSearchResponseDTO fromPriceToPriceSearchResponseDTO(Price price);
}
