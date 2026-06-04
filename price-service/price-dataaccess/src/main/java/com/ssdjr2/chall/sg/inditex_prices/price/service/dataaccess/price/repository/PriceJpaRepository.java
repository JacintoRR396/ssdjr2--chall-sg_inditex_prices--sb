package com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.repository;

import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

	@Query("""
   SELECT p
   FROM PriceEntity p
   WHERE p.brandId = :brandId
   AND p.productId = :productId
   AND :applicationDate BETWEEN p.startDate AND p.endDate
   ORDER BY p.priority DESC
   LIMIT 1""")
	Optional<PriceEntity> findPriceByBrandIdAndProductIdAndApplicationDate(
			Long brandId,
			Integer productId,
			LocalDateTime applicationDate
	);
}
