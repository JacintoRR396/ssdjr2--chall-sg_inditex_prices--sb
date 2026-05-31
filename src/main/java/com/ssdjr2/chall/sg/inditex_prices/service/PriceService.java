package com.ssdjr2.chall.sg.inditex_prices.service;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.PriceSearchResponseDTO;

public interface PriceService {
	PriceSearchResponseDTO search(PriceSearchQueryDTO body);
}
