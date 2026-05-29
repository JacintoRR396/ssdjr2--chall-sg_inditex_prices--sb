package com.ssdjr2.chall.sg.inditex_prices.controllers;

import com.ssdjr2.chall.sg.inditex_prices.domain.dtos.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.domain.dtos.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.services.PriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prices")
public class PriceController {

	private final PriceService priceService;

	@GetMapping("/search")
	public ResponseEntity<PriceSearchResponseDTO> search(@Valid @RequestBody PriceSearchQueryDTO body) {

		PriceSearchResponseDTO res = this.priceService.search(body);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
