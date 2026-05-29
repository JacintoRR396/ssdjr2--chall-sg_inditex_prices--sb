package com.ssdjr2.chall.sg.inditex_prices.domain.mappers;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import com.ssdjr2.chall.sg.inditex_prices.repositories.entities.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandMapper.class, ApplicationDatesMapper.class, MoneyMapper.class})
public interface PriceMapper {

	/*
	 * ENTITY -> DOMAIN
	 */
	@Mapping(target = "applicationDates",	source = "entity")
	@Mapping(target = "money", source = "entity")
	Price fromPriceEntityToPrice(PriceEntity entity);
}
