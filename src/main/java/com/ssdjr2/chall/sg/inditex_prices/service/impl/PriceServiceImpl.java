package com.ssdjr2.chall.sg.inditex_prices.service.impl;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.mapper.PriceDtoMapper;
import com.ssdjr2.chall.sg.inditex_prices.domain.exception.BrandNotFoundException;
import com.ssdjr2.chall.sg.inditex_prices.domain.exception.PriceNotFoundException;
import com.ssdjr2.chall.sg.inditex_prices.persistence.mapper.PriceEntityMapper;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import com.ssdjr2.chall.sg.inditex_prices.persistence.repository.BrandRepository;
import com.ssdjr2.chall.sg.inditex_prices.persistence.repository.PriceRepository;
import com.ssdjr2.chall.sg.inditex_prices.persistence.entity.PriceEntity;
import com.ssdjr2.chall.sg.inditex_prices.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

	private final PriceDtoMapper priceDtoMapper;
	private final PriceEntityMapper priceEntityMapper;
	private final PriceRepository priceRepository;
	private final BrandRepository brandRepository;

	@Override
	@Transactional(readOnly = true)
	public PriceSearchResponseDTO search(PriceSearchQueryDTO body) {
		this.checkBrandExists( body.brandId() );

		Price priceSearch = this.priceDtoMapper.fromPriceQueryDTOToPrice(body);
		Optional<PriceEntity> priceEntityOpt = this.priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
				priceSearch.getBrand().getId(), priceSearch.getProductId(), priceSearch.getApplicationDates().getStartDate());

		if(priceEntityOpt.isPresent()) {
			PriceEntity priceEntity = priceEntityOpt.get();
			Price priceFound = this.priceEntityMapper.fromPriceEntityToPrice(priceEntity);

			return this.priceDtoMapper.fromPriceToPriceSearchResponseDTO(priceFound);
		}

		throw new PriceNotFoundException( priceSearch.getBrand().getId(), priceSearch.getProductId() );
	}

	private void checkBrandExists( Long brandId ) {
		this.brandRepository.findById( brandId )
				.orElseThrow( ()-> new BrandNotFoundException( brandId ) );
	}
}
