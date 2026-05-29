package com.ssdjr2.chall.sg.inditex_prices.services;

import com.ssdjr2.chall.sg.inditex_prices.domain.dtos.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.dtos.PriceSearchResponseDTO;

public interface PriceService {
	PriceSearchResponseDTO search(PriceSearchQueryDTO body);
}
