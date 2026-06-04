package com.ssdjr2.chall.sg.inditex_prices.price.service.domain.factory;

import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.response.ApplicationDatesResponseDTO;

import java.time.LocalDateTime;

public class ApplicationDatesResponseDTOMother {

	private ApplicationDatesResponseDTOMother() {
		throw new UnsupportedOperationException();
	}

	public static ApplicationDatesResponseDTO createApplicationDatesResponseDTOAboutTariff1() {
		return new ApplicationDatesResponseDTO(
				LocalDateTime.of( 2020, 6, 14, 0, 0 ),
				LocalDateTime.of( 2020, 12, 31, 23, 59, 59 )
		);
	}
}
