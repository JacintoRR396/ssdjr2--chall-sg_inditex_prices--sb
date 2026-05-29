package com.ssdjr2.chall.sg.inditex_prices.domain.mappers;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Brand;
import com.ssdjr2.chall.sg.inditex_prices.repositories.entities.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

	/*
	 * ENTITY -> DOMAIN
	 */
	Brand fromBrandEntityToBrand(BrandEntity entity);
}
