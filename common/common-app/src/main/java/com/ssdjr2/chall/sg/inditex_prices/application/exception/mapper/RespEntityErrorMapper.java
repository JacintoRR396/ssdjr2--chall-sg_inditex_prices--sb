package com.ssdjr2.chall.sg.inditex_prices.application.exception.mapper;

import com.ssdjr2.chall.sg.inditex_prices.application.exception.custom.CustomException;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.error.RespEntityErrorDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class RespEntityErrorMapper {

	@Mapping(source = "ex.id", target = "id")
	@Mapping(source = "timestamp", target = "timestamp")
	@Mapping(target = "httpStatusCode", ignore = true)
	@Mapping(source = "ex.appExCode.appStatusCode", target = "errorCode")
	@Mapping(source = "ex.appExCode.message", target = "errorMessage")
	@Mapping(target = "validationErrors", ignore = true)
	@Mapping(target = "exMessage", ignore = true)
	@Mapping(target = "exTrackTrace", ignore = true)
	public abstract RespEntityErrorDTO toDTO(CustomException ex, String timestamp );

	@AfterMapping
	protected void afterMappingTDTO(CustomException ex, @MappingTarget RespEntityErrorDTO errorDTO) {
		if (Objects.nonNull(ex.getAppExCode()) && Objects.nonNull(ex.getAppExCode().getHttpStatusCode())) {
			errorDTO.setHttpStatusCode(ex.getAppExCode().getHttpStatusCode().value());
		}

		if (Objects.nonNull(ex.getValidationErrors())) {
			errorDTO.setValidationErrors(ex.getValidationErrors());
		}

		if (Objects.nonNull(ex.getOriginalException())) {
			Throwable original = ex.getOriginalException();
			errorDTO.setExMessage(original.getMessage());

			StringWriter sw = new StringWriter();
			original.printStackTrace(new PrintWriter(sw));
			errorDTO.setExTrackTrace(sw.toString());
		}
	}
}
