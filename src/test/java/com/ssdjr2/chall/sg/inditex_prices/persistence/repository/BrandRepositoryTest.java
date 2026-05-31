package com.ssdjr2.chall.sg.inditex_prices.persistence.repository;

import com.ssdjr2.chall.sg.inditex_prices.factory.FactoryBrand;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.BrandEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BrandRepositoryTest {

	@Autowired
	private BrandRepository brandRepository;

	@Test
	@DisplayName("Given existing brand id when findById then return brand")
	void givenExistingBrandId_whenFindById_thenReturnBrand() {
		Long brandId = 1L;

		Optional<BrandEntity> result = this.brandRepository.findById(brandId);

		assertTrue(result.isPresent());
		assertEquals(1L, result.get().getId());
		assertEquals(FactoryBrand.ZARA, result.get().getName());
	}

	@Test
	@DisplayName("Given non existing brand id when findById then return empty optional")
	void givenNonExistingBrandId_whenFindById_thenReturnEmptyOptional() {
		Long brandId = 999L;

		Optional<BrandEntity> result = this.brandRepository.findById(brandId);

		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("Given initialized database when count then return loaded brands")
	void givenInitializedDatabase_whenCount_thenReturnLoadedBrands() {
		long expectedBrands = 1L;

		long result = this.brandRepository.count();

		assertEquals(expectedBrands, result);
	}
}