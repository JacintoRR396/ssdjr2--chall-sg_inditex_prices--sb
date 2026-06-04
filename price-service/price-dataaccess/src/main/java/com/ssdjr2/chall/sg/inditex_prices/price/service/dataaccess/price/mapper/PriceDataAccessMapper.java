package com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.valueobject.identifier.PriceId;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.entity.PriceEntity;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ApplicationDatesDataAccessMapper.class, MoneyDataAccessMapper.class})
public interface PriceDataAccessMapper {

	/*
	 * ENTITY -> DOMAIN
	 */
	@Mapping(target = "brandId.id",	source = "brandId")
	@Mapping(target = "productId.id",	source = "productId")
	@Mapping(target = "applicationDates",	source = "entity")
	@Mapping(target = "money", source = "entity")
	Price fromPriceEntityToPrice(PriceEntity entity);

	default PriceId map(Long id) {
		return id != null ? new PriceId(id) : null;
	}
}
