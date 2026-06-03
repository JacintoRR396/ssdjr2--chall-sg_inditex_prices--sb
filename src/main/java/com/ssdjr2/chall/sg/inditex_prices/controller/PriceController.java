package com.ssdjr2.chall.sg.inditex_prices.controller;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.service.PriceService;
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

	private final PriceService priceService;

	@PostMapping("/search")
	public ResponseEntity<PriceSearchResponseDTO> search(@Valid @RequestBody PriceSearchQueryDTO body) {
		PriceSearchResponseDTO res = this.priceService.search(body);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
