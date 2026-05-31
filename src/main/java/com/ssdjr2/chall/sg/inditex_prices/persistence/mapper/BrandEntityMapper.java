package com.ssdjr2.chall.sg.inditex_prices.persistence.mapper;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Brand;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {

	/*
	 * ENTITY -> DOMAIN
	 */
	Brand fromBrandEntityToBrand(BrandEntity entity);
}
