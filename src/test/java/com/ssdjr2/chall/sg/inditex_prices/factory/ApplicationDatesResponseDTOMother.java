package com.ssdjr2.chall.sg.inditex_prices.factory;

import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.ApplicationDatesResponseDTO;

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

	public static ApplicationDatesResponseDTO createApplicationDatesResponseDTOAboutTariff2() {
		return new ApplicationDatesResponseDTO(
				LocalDateTime.of( 2020, 6, 14, 15, 0 ),
				LocalDateTime.of( 2020, 6, 14, 18, 30 )
		);
	}

	public static ApplicationDatesResponseDTO createApplicationDatesResponseDTOAboutTariff3() {
		return new ApplicationDatesResponseDTO(
				LocalDateTime.of( 2020, 6, 15, 0, 0 ),
				LocalDateTime.of( 2020, 6, 15, 11, 0 )
		);
	}

	public static ApplicationDatesResponseDTO createApplicationDatesResponseDTOAboutTariff4() {
		return new ApplicationDatesResponseDTO(
				LocalDateTime.of( 2020, 6, 15, 16, 0 ),
				LocalDateTime.of( 2020, 12, 31, 23, 59, 59 )
		);
	}
}
