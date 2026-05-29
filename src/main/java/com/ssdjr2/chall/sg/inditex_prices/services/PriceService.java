package com.ssdjr2.chall.sg.inditex_prices.services;

import com.ssdjr2.chall.sg.inditex_prices.controllers.dtos.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controllers.dtos.response.PriceSearchResponseDTO;

public interface PriceService {
	PriceSearchResponseDTO search(PriceSearchQueryDTO body);
}
