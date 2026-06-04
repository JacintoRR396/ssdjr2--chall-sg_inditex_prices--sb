package com.ssdjr2.chall.sg.inditex_prices.price.service.application.rest;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.ports.input.service.PriceApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prices")
public class PriceController {

	private final PriceApplicationService priceApplicationService;

	@PostMapping("/search")
	public ResponseEntity<PriceSearchResponseDTO> search(@Valid @RequestBody PriceSearchQueryDTO body) {
		PriceSearchResponseDTO res = this.priceApplicationService.searchPrice(body);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
