package com.ssdjr2.chall.sg.inditex_prices.controllers.mappers;

import com.ssdjr2.chall.sg.inditex_prices.controllers.dtos.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controllers.dtos.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandDtoMapper.class, ApplicationDatesDtoMapper.class, MoneyDtoMapper.class})
public interface PriceDtoMapper {

	/*
	 * DTO -> DOMAIN
	 */
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "brandId", target = "brand")
	@Mapping(source = "productId", target = "productId")
	@Mapping(target = "priceList", ignore = true)
	@Mapping(target = "priority", ignore = true)
	@Mapping(source = "applicationDate", target = "applicationDates")
	@Mapping(target = "money", ignore = true)
	Price fromPriceQueryDTOToPrice(PriceSearchQueryDTO dto);

	/*
	 * DOMAIN -> DTO
	 */
	@Mapping(source = "brand.id", target = "brandId")
	PriceSearchResponseDTO fromPriceToPriceSearchResponseDTO(Price price);
}
