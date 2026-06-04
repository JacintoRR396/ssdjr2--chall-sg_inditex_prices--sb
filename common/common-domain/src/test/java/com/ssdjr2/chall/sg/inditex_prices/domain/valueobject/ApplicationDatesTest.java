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
		LocalDateTime start = ApplicationDatesMother.test2();
		LocalDateTime end = ApplicationDatesMother.test1();

		DomainException exception = assertThrows(DomainException.class, () ->
				new ApplicationDates(start, end)
		);
		assertEquals("error.dates.invalid_range", exception.getMessage());
	}

	@Test
	@DisplayName("GIVEN two ApplicationDates with same dates WHEN comparing THEN they are equal")
	void givenSameDates_whenComparing_thenAreEqual() {
		LocalDateTime start = ApplicationDatesMother.test1();
		LocalDateTime end = ApplicationDatesMother.test2();
		ApplicationDates dates1 = new ApplicationDates(start, end);
		ApplicationDates dates2 = new ApplicationDates(start, end);

		assertEquals(dates1, dates2);
		assertEquals(dates1.hashCode(), dates2.hashCode());
	}

	@Test
	@DisplayName("GIVEN two ApplicationDates with different dates WHEN comparing THEN they are not equal")
	void givenDifferentDates_whenComparing_thenAreNotEqual() {
		ApplicationDates dates1 = new ApplicationDates(
				ApplicationDatesMother.test1(), ApplicationDatesMother.test2());
		ApplicationDates dates2 = new ApplicationDates(
				ApplicationDatesMother.test1(), ApplicationDatesMother.test2().plusHours(1));

		assertNotEquals(dates1, dates2);
		assertNotEquals(dates1.hashCode(), dates2.hashCode());
	}

	@Test
	@DisplayName("GIVEN null or different class WHEN comparing THEN return false")
	void givenDifferentTypes_whenComparing_thenReturnFalse() {
		ApplicationDates dates = new ApplicationDates(
				ApplicationDatesMother.test1(), ApplicationDatesMother.test2());

		assertNotEquals(null, dates, "Should not be equal to null");
		assertNotEquals("A String", dates, "Should not be equal to a different class");
	}
}
