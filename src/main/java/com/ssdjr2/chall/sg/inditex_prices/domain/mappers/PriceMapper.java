package com.ssdjr2.chall.sg.inditex_prices.domain.mappers;

import com.ssdjr2.chall.sg.inditex_prices.domain.dtos.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.dtos.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import com.ssdjr2.chall.sg.inditex_prices.repositories.entities.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandMapper.class, ApplicationDatesMapper.class, MoneyMapper.class})
public interface PriceMapper {

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
	 * ENTITY -> DOMAIN
	 */
	@Mapping(target = "applicationDates",	source = "entity")
	@Mapping(target = "money", source = "entity")
	Price fromPriceEntityToPrice(PriceEntity entity);

	/*
	 * DOMAIN -> DTO
	 */
	@Mapping(source = "brand.id", target = "brandId")
	PriceSearchResponseDTO fromPriceToPriceSearchResponseDTO(Price price);
}
