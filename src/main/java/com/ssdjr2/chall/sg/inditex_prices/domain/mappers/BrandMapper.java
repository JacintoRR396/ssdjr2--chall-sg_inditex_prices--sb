package com.ssdjr2.chall.sg.inditex_prices.domain.mappers;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Brand;
import com.ssdjr2.chall.sg.inditex_prices.repositories.entities.BrandEntity;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface BrandMapper {

	/*
	 * DTO -> DOMAIN
	 */
	default Brand fromIdToBrand(Integer brandId) {

		if( Objects.isNull(brandId) ) {
			return null;
		}

		return Brand.builder()
				.id(brandId.longValue())
				.build();
	}

	/*
	 * ENTITY -> DOMAIN
	 */
	Brand fromBrandEntityToBrand(BrandEntity entity);
}
