package com.ssdjr2.chall.sg.inditex_prices.controllers.mappers;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Brand;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface BrandDtoMapper {

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
}
