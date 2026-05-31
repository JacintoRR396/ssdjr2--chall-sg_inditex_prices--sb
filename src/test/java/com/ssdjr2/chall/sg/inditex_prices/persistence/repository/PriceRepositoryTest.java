package com.ssdjr2.chall.sg.inditex_prices.persistence.repository;

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
	void givenDate202006141000_whenSearch_thenReturnTariff1() {
		LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

		Optional<PriceEntity> result =
				priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate( 1L, 35455, applicationDate );

		assertThat(result).isPresent();

		PriceEntity price = result.get();
		assertThat(price.getPriceList()).isEqualTo(1);
		assertThat(price.getPriority()).isZero();
		assertThat(price.getPrice()).isEqualByComparingTo(new BigDecimal("35.50"));
	}

	@Test
	@DisplayName("Given 2020-06-14 16:00 when search then return tariff 2")
	void givenDate202006141600_whenSearch_thenReturnTariff2() {
		LocalDateTime applicationDate =	LocalDateTime.of(2020, 6, 14, 16, 0);

		Optional<PriceEntity> result =
				priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate( 1L, 35455, applicationDate );

		assertThat(result).isPresent();

		PriceEntity price = result.get();
		assertThat(price.getPriceList()).isEqualTo(2);
		assertThat(price.getPriority()).isEqualTo(1);
		assertThat(price.getPrice()).isEqualByComparingTo(new BigDecimal("25.45"));
	}

	@Test
	@DisplayName("Given 2020-06-14 21:00 when search then return tariff 1")
	void givenDate202006142100_whenSearch_thenReturnTariff1() {
		LocalDateTime applicationDate =	LocalDateTime.of(2020, 6, 14, 21, 0);

		Optional<PriceEntity> result =
				priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate( 1L, 35455, applicationDate );

		assertThat(result).isPresent();
		assertThat(result.get().getPriceList()).isEqualTo(1);
		assertThat(result.get().getPrice()).isEqualByComparingTo(new BigDecimal("35.50"));
	}

	@Test
	@DisplayName("Given 2020-06-15 10:00 when search then return tariff 3")
	void givenDate202006151000_whenSearch_thenReturnTariff3() {
		LocalDateTime applicationDate =	LocalDateTime.of(2020, 6, 15, 10, 0);

		Optional<PriceEntity> result =
				priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate( 1L, 35455, applicationDate );

		assertThat(result).isPresent();

		PriceEntity price = result.get();
		assertThat(price.getPriceList()).isEqualTo(3);
		assertThat(price.getPriority()).isEqualTo(1);
		assertThat(price.getPrice()).isEqualByComparingTo(new BigDecimal("30.50"));
	}

	@Test
	@DisplayName("Given 2020-06-16 21:00 when search then return tariff 4")
	void givenDate202006162100_whenSearch_thenReturnTariff4() {
		LocalDateTime applicationDate =	LocalDateTime.of(2020, 6, 16, 21, 0);

		Optional<PriceEntity> result =
				priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate( 1L, 35455,	applicationDate	);

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

		Optional<PriceEntity> result =
				priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate( 999L, 35455, applicationDate );

		assertThat(result).isEmpty();
	}

	@Test
	@DisplayName("Given non existing product when search then return empty")
	void givenNonExistingProduct_whenSearch_thenReturnEmpty() {
		LocalDateTime applicationDate =	LocalDateTime.of(2020, 6, 14, 10, 0);

		Optional<PriceEntity> result =
				priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate( 1L, 99999,	applicationDate	);

		assertThat(result).isEmpty();
	}
}