package com.ssdjr2.chall.sg.inditex_prices.repositories;

import com.ssdjr2.chall.sg.inditex_prices.repositories.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

	@Query(value = """
			SELECT p.BRAND_ID, p.PRODUCT_ID, p.PRICE_LIST, p.START_DATE, p.END_DATE, p.PRICE, p.CURRENCY
			FROM ECOMMERCE.PRICES p
			WHERE p.BRAND_ID = :brandId
			AND p.PRODUCT_ID = :productId
			AND :applicationDate BETWEEN p.START_DATE AND p.END_DATE
			ORDER BY p.PRIORITY DESC
			LIMIT 1
			""", nativeQuery = true)
	Optional<PriceEntity> findPriceByBrandIdAndProductIdAndApplicationDate(@Param("brandId") Integer brandId, @Param("productId") Integer productId, @Param("applicationDate") LocalDateTime applicationDate);
}
