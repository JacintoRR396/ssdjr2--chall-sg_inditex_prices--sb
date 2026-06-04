package com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.adapter;

import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.mapper.PriceDataAccessMapper;
import com.ssdjr2.chall.sg.inditex_prices.price.service.dataaccess.price.repository.PriceJpaRepository;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.ports.output.repository.PriceRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

	private final PriceDataAccessMapper priceDataAccessMapper;
	private final PriceJpaRepository priceJpaRepository;

	@Override
	public Optional<Price> findById(Price price) {
		return this.priceJpaRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
				price.getBrandId().getId(), price.getProductId().getId(), price.getApplicationDates().startDate())
				.map(this.priceDataAccessMapper::fromPriceEntityToPrice);
	}
}
