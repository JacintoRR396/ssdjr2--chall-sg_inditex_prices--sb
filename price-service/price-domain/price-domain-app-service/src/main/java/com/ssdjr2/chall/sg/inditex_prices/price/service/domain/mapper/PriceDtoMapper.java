package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.mapper;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ApplicationDatesDtoMapper.class, MoneyDtoMapper.class})
public interface PriceDtoMapper {

	/*
	 * DTO -> DOMAIN
	 */
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "brandId", target = "brandId.id")
	@Mapping(source = "productId", target = "productId.id")
	@Mapping(target = "priceList", ignore = true)
	@Mapping(target = "priority", ignore = true)
	@Mapping(source = "applicationDate", target = "applicationDates")
	@Mapping(target = "money", ignore = true)
	Price fromPriceQueryDTOToPrice(PriceSearchQueryDTO dto);

	/*
	 * DOMAIN -> DTO
	 */
	@Mapping(source = "brandId.id", target = "brandId")
	@Mapping(source = "productId.id", target = "productId")
	PriceSearchResponseDTO fromPriceToPriceSearchResponseDTO(Price price);
}
