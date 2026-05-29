package com.ssdjr2.chall.sg.inditex_prices.services.impl;

import com.ssdjr2.chall.sg.inditex_prices.domain.dtos.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.dtos.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.mappers.PriceMapper;
import com.ssdjr2.chall.sg.inditex_prices.domain.model.Price;
import com.ssdjr2.chall.sg.inditex_prices.repositories.PriceRepository;
import com.ssdjr2.chall.sg.inditex_prices.repositories.entities.PriceEntity;
import com.ssdjr2.chall.sg.inditex_prices.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PrinceServiceImpl implements PriceService {

	private final PriceMapper priceMapper;
	private final PriceRepository priceRepository;

	@Override
	public PriceSearchResponseDTO search(PriceSearchQueryDTO body) {
		Price priceSearch = this.priceMapper.fromPriceQueryDTOToPrice(body);
		Optional<PriceEntity> priceEntityOpt = this.priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
				priceSearch.getBrand().getId(), priceSearch.getProductId(), priceSearch.getApplicationDates().getStartDate());

		if(priceEntityOpt.isPresent()) {
			PriceEntity priceEntity = priceEntityOpt.get();
			Price priceFound = this.priceMapper.fromPriceEntityToPrice(priceEntity);
			return this.priceMapper.fromPriceToPriceSearchResponseDTO(priceFound);
		}

		return null;
	}
}
