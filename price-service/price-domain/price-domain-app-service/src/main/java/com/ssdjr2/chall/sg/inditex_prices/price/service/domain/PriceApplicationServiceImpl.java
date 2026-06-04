package com.ssdjr2.chall.sg.inditex_prices.price.service.domain;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.entity.Price;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.exception.PriceNotFoundException;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.mapper.PriceDtoMapper;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.ports.input.service.PriceApplicationService;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.ports.output.repository.PriceRepository;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.service.PriceDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class PriceApplicationServiceImpl implements PriceApplicationService {

	private final PriceDomainService priceDomainService;

	private final PriceDtoMapper priceDtoMapper;

	private final PriceRepository priceRepository;

	public PriceApplicationServiceImpl(PriceDomainService priceDomainService, PriceDtoMapper priceDtoMapper, PriceRepository priceRepository) {
		this.priceDomainService = priceDomainService;
		this.priceDtoMapper = priceDtoMapper;
		this.priceRepository = priceRepository;
	}

	@Override
	public PriceSearchResponseDTO searchPrice(PriceSearchQueryDTO body) {
		Price priceSearch = this.priceDtoMapper.fromPriceQueryDTOToPrice(body);
		this.priceDomainService.validateAndInitiatePrice(priceSearch);

			return this.priceRepository.findById(priceSearch)
					.map(priceDtoMapper::fromPriceToPriceSearchResponseDTO)
					.orElseThrow( () -> new PriceNotFoundException( priceSearch.getBrandId().getId(), priceSearch.getProductId().getId() ));
	}
}
