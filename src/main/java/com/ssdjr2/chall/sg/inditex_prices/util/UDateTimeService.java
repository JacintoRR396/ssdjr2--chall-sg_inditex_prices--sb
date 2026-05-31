package com.ssdjr2.chall.sg.inditex_prices.util;

import com.ssdjr2.chall.sg.inditex_prices.config.properties.GlobalProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UDateTimeService {

	/**
	 * Global Properties about Logs, Constants, Regular Expression, etc
	 */
	private final GlobalProperties globalProperties;

	/* LocalDateTime */
	public String parseLocalDateTimeToString ( final LocalDateTime value, final String format ) {
		if ( Objects.nonNull( value ) && Objects.nonNull( format ) ) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern( format );
			return value.format( formatter );
		}

		return null;
	}

	public String getTimestamp () {
		// Get the current time with the UTC time zone offset
		OffsetDateTime currentTime = OffsetDateTime.now();

		// Create a DateTimeFormatter for the desired format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				this.globalProperties.getFormatTimestampBackend() );

		// Format the current time using the formatter
		return currentTime.format( formatter );
	}
}
