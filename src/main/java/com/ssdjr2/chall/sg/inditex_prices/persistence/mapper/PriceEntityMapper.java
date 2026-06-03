package com.ssdjr2.chall.sg.inditex_prices.persistence.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ApplicationDatesEntityMapper.class, MoneyEntityMapper.class})
public interface PriceEntityMapper {

	/*
	 * ENTITY -> DOMAIN
	 */
	@Mapping(target = "applicationDates",	source = "entity")
	@Mapping(target = "money", source = "entity")
	Price fromPriceEntityToPrice(PriceEntity entity);
}
