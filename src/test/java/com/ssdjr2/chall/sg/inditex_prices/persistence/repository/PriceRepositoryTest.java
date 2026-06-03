package com.ssdjr2.chall.sg.inditex_prices.persistence.repository;

import com.ssdjr2.chall.sg.inditex_prices.factory.ApplicationDatesMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.BrandMother;
import com.ssdjr2.chall.sg.inditex_prices.factory.PriceMother;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PriceRepositoryTest {

	@Autowired
	private PriceRepository priceRepository;

	@Test
	@DisplayName("Given 2020-06-14 10:00 when search then return tariff 1")
	void givenDateAboutTest1_whenSearch_thenReturnTariff1() {
		LocalDateTime applicationDate = ApplicationDatesMother.test1();

		Optional<PriceEntity> result = priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
						BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, applicationDate );

		assertThat(result).isPresent();

		PriceEntity price = result.get();
		assertThat(price.getPriceList()).isEqualTo( PriceMother.PRICE_LIST_TARIFF1 );
		assertThat(price.getPriority()).isZero();
		assertThat(price.getPrice()).isEqualByComparingTo(new BigDecimal("35.50"));
	}

	@Test
	@DisplayName("Given 2020-06-14 16:00 when search then return tariff 2")
	void givenDateAboutTest2_whenSearch_thenReturnTariff2() {
		LocalDateTime applicationDate =	ApplicationDatesMother.test2();

		Optional<PriceEntity> result = priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
						BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, applicationDate );

		assertThat(result).isPresent();

		PriceEntity price = result.get();
		assertThat(price.getPriceList()).isEqualTo(2);
		assertThat(price.getPriority()).isEqualTo(1);
		assertThat(price.getPrice()).isEqualByComparingTo(new BigDecimal("25.45"));
	}

	@Test
	@DisplayName("Given 2020-06-14 21:00 when search then return tariff 1")
	void givenDateAboutTest3_whenSearch_thenReturnTariff1() {
		LocalDateTime applicationDate =	ApplicationDatesMother.test3();

		Optional<PriceEntity> result = priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
						BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, applicationDate );

		assertThat(result).isPresent();
		assertThat(result.get().getPriceList()).isEqualTo(1);
		assertThat(result.get().getPrice()).isEqualByComparingTo(new BigDecimal("35.50"));
	}

	@Test
	@DisplayName("Given 2020-06-15 10:00 when search then return tariff 3")
	void givenDateAboutTest4_whenSearch_thenReturnTariff3() {
		LocalDateTime applicationDate =	ApplicationDatesMother.test4();

		Optional<PriceEntity> result = priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
						BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, applicationDate );

		assertThat(result).isPresent();

		PriceEntity price = result.get();
		assertThat(price.getPriceList()).isEqualTo(3);
		assertThat(price.getPriority()).isEqualTo(1);
		assertThat(price.getPrice()).isEqualByComparingTo(new BigDecimal("30.50"));
	}

	@Test
	@DisplayName("Given 2020-06-16 21:00 when search then return tariff 4")
	void givenDateAboutTest5_whenSearch_thenReturnTariff4() {
		LocalDateTime applicationDate =	ApplicationDatesMother.test5();

		Optional<PriceEntity> result = priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
						BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID, applicationDate	);

		assertThat(result).isPresent();

		PriceEntity price = result.get();
		assertThat(price.getPriceList()).isEqualTo(4);
		assertThat(price.getPriority()).isEqualTo(1);
		assertThat(price.getPrice()).isEqualByComparingTo(new BigDecimal("38.95"));
	}

	@Test
	@DisplayName("Given non existing brand when search then return empty")
	void givenNonExistingBrand_whenSearch_thenReturnEmpty() {
		LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

		Optional<PriceEntity> result = priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
						BrandMother.INVALID_ID, PriceMother.PRODUCT_ID_VALID, applicationDate );

		assertThat(result).isEmpty();
	}

	@Test
	@DisplayName("Given non existing product when search then return empty")
	void givenNonExistingProduct_whenSearch_thenReturnEmpty() {
		LocalDateTime applicationDate =	LocalDateTime.of(2020, 6, 14, 10, 0);

		Optional<PriceEntity> result = priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
						BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_INVALID,	applicationDate	);

		assertThat(result).isEmpty();
	}
}
