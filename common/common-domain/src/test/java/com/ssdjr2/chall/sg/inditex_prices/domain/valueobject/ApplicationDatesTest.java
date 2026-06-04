package com.ssdjr2.chall.sg.inditex_prices.domain.valueobject;

import com.ssdjr2.chall.sg.inditex_prices.domain.exception.DomainException;
import com.ssdjr2.chall.sg.inditex_prices.domain.factory.ApplicationDatesMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationDatesTest {

	@Test
	@DisplayName("GIVEN valid dates WHEN creating record THEN record is instantiated")
	void givenValidDates_whenCreatingRecord_thenSuccess() {
		LocalDateTime start = ApplicationDatesMother.test1();
		LocalDateTime end = ApplicationDatesMother.test2();

		ApplicationDates dates = new ApplicationDates( start, end );

		assertNotNull(dates);
		assertEquals(start, dates.startDate());
		assertEquals(end, dates.endDate());
	}

	@Test
	@DisplayName("GIVEN null startDate WHEN creating record THEN throw DomainException")
	void givenNullStartDate_whenCreatingRecord_thenThrowException() {
		LocalDateTime end = LocalDateTime.now();

		DomainException exception = assertThrows(DomainException.class, () ->
				new ApplicationDates(null, end)
		);

		assertEquals("error.dates.start.null", exception.getMessage());
	}

	@Test
	@DisplayName("GIVEN null endDate WHEN creating record THEN throw DomainException")
	void givenNullEndDate_whenCreatingRecord_thenThrowException() {
		LocalDateTime start = LocalDateTime.now();

		DomainException exception = assertThrows(DomainException.class, () ->
				new ApplicationDates(start, null)
		);

		assertEquals("error.dates.end.null", exception.getMessage());
	}

	@Test
	@DisplayName("GIVEN endDate before startDate WHEN creating record THEN throw DomainException")
	void givenInvalidRange_whenCreatingRecord_thenThrowException() {
		// GIVEN
		LocalDateTime start = ApplicationDatesMother.test2();
		LocalDateTime end = ApplicationDatesMother.test1();

		// WHEN & THEN
		DomainException exception = assertThrows(DomainException.class, () ->
				new ApplicationDates(start, end)
		);
		assertEquals("error.dates.invalid_range", exception.getMessage());
	}
}
