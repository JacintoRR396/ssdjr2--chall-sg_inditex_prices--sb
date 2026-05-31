package com.ssdjr2.chall.sg.inditex_prices.persistence.repository;

import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

	/*
	@Query("""
   SELECT p
   FROM PriceEntity p
   WHERE p.brand.id = :brandId
   AND p.productId = :productId
   AND :applicationDate BETWEEN p.startDate AND p.endDate
   ORDER BY p.priority DESC
   LIMIT 1""")
	*/
	Optional<PriceEntity> findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
			Long brandId,
			Integer productId,
			LocalDateTime applicationDateEqualOrAfter,
			LocalDateTime applicationDateEqualOrBefore
	);
}
