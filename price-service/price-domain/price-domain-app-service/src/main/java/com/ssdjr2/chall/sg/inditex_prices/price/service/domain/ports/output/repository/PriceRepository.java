package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.ports.output.repository;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;

import java.util.Optional;

public interface PriceRepository {

	Optional<Price> findById(Price price);
}
