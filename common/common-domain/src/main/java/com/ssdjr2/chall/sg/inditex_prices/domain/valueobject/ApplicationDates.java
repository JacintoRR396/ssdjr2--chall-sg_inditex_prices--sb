package com.ssdjr2.chall.sg.inditex_prices.domain.valueobject;

import com.ssdjr2.chall.sg.inditex_prices.domain.exception.DomainException;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder(toBuilder = true)
public record ApplicationDates(LocalDateTime startDate, LocalDateTime endDate) {

	public ApplicationDates {
		this.checkArgs(startDate, endDate);
	}

	@Override
	public boolean equals(Object o) {
		if( o == null || getClass() != o.getClass() ) return false;
		ApplicationDates that = (ApplicationDates) o;
		return Objects.equals(endDate, that.endDate) && Objects.equals(startDate, that.startDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(startDate, endDate);
	}

	private void checkArgs(LocalDateTime startDate, LocalDateTime endDate) {
		if( startDate == null ) {
			throw new DomainException("error.dates.start.null");
		}
		if( endDate == null ) {
			throw new DomainException("error.dates.end.null");
		}

		if( isAfter(startDate, endDate) ) {
			throw new DomainException("error.dates.invalid_range");
		}
	}

	public static boolean isAfter(LocalDateTime startDate, LocalDateTime endDate) {
		return startDate.isAfter(endDate);
	}
}
