package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.ports.input.service;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.PriceSearchResponseDTO;
import jakarta.validation.Valid;

public interface PriceApplicationService {

	PriceSearchResponseDTO searchPrice(@Valid PriceSearchQueryDTO priceSearchQueryDTO);
}
