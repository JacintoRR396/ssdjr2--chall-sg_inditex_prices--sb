package com.ssdjr2.chall.sg.inditex_prices.factory;

import com.ssdjr2.chall.sg.inditex_prices.domain.model.Brand;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.BrandEntity;

public final class FactoryBrand {

	public static final Long ZARA_ID = 1L;
	public static final String ZARA_NAME = "ZARA";
	public static final Long INVALID_ID = 999L;

	public static BrandEntity createBrandEntityZara() {
		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setId(ZARA_ID);
		brandEntity.setName(ZARA_NAME);

		return brandEntity;
	}

	public static Brand createBrandZara() {
		return Brand.builder()
						.id(ZARA_ID)
						.name(ZARA_NAME)
						.build();
		}
}
