package com.ssdjr2.chall.sg.inditex_prices.application.exception.mapper;

import com.ssdjr2.chall.sg.inditex_prices.application.exception.AppExceptionCodeEnum;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.custom.CustomException;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.error.RespEntityErrorDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RespEntityErrorMapperTest {

	private final RespEntityErrorMapper mapper = Mappers.getMapper(RespEntityErrorMapper.class);

	@Test
	void givenCustomExceptionWithValidationErrorsAndOriginalException_whenToDTOIsCalled_thenDtoIsMappedCorrectly() {
		String expectedTimestamp = "2026-06-04T12:00:00Z";
		Map<String, String> expectedValidationErrors = Map.of("price.id", "Must not be null");
		AppExceptionCodeEnum appExCode = AppExceptionCodeEnum.STATUS_40001;
		IllegalArgumentException originalException = new IllegalArgumentException("Invalid price input format");
		CustomException customException = new CustomException(
				originalException,
				appExCode,
				expectedValidationErrors
		);

		RespEntityErrorDTO result = mapper.toDTO(customException, expectedTimestamp);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(customException.getId());
		assertThat(result.getTimestamp()).isEqualTo(expectedTimestamp);
		assertThat(result.getHttpStatusCode()).isEqualTo(400);
		assertThat(result.getErrorCode()).isEqualTo(40001);
		assertThat(result.getErrorMessage()).isEqualTo("Bad Request: Validation errors");

		assertThat(result.getValidationErrors()).containsAllEntriesOf(expectedValidationErrors);
		assertThat(result.getExMessage()).isEqualTo(originalException.getMessage());
		assertThat(result.getExTrackTrace())
				.contains("java.lang.IllegalArgumentException: Invalid price input format");
	}

	@Test
	void givenCustomExceptionWithoutOptionalFields_whenToDTOIsCalled_thenDtoHasNullOptionals() {
		String expectedTimestamp = "2026-06-04T12:00:00Z";
		AppExceptionCodeEnum appExCode = AppExceptionCodeEnum.STATUS_40401;
		CustomException customException = new CustomException(null, appExCode, null);

		RespEntityErrorDTO result = mapper.toDTO(customException, expectedTimestamp);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(customException.getId());
		assertThat(result.getHttpStatusCode()).isEqualTo(404);
		assertThat(result.getErrorCode()).isEqualTo(40401);
		assertThat(result.getErrorMessage()).isEqualTo("Price not Found");

		assertThat(result.getValidationErrors()).isNull();
		assertThat(result.getExMessage()).isNull();
		assertThat(result.getExTrackTrace()).isNull();
	}
}
