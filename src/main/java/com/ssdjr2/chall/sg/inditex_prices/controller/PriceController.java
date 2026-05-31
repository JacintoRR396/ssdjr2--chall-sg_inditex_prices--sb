package com.ssdjr2.chall.sg.inditex_prices.controller;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.exception.AppExceptionCodeEnum;
import com.ssdjr2.chall.sg.inditex_prices.exception.custom.CustomException;
import com.ssdjr2.chall.sg.inditex_prices.service.PriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prices")
public class PriceController {

	private final PriceService priceService;

	@PostMapping("/search")
	public ResponseEntity<PriceSearchResponseDTO> search(@Valid @RequestBody PriceSearchQueryDTO body,
	                                                     BindingResult resValidation) {
		this.checkValidation( resValidation );

		PriceSearchResponseDTO res = this.priceService.search(body);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	private void checkValidation(BindingResult resValidation) {
		if (resValidation.hasFieldErrors()) {
			Map<String, String> validationErrors = new HashMap<>();
			resValidation.getFieldErrors().forEach(error -> {
				String key = error.getField();
				String value = error.getField();
				validationErrors.put(key, "The field " + value + " " + error.getDefaultMessage());
			});
			throw new CustomException(null, AppExceptionCodeEnum.STATUS_40001, validationErrors);
		}
	}
}
