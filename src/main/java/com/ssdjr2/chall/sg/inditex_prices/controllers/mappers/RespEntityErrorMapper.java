package com.ssdjr2.chall.sg.inditex_prices.controllers.mappers;

import com.ssdjr2.chall.sg.inditex_prices.controllers.dtos.error.RespEntityErrorDTO;
import com.ssdjr2.chall.sg.inditex_prices.exceptions.custom.CustomException;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Arrays;
import java.util.Objects;

@Mapper(componentModel = "spring", imports = { Arrays.class })
public abstract class RespEntityErrorMapper {

	@Mapping(source = "ex.id", target = "id")
	@Mapping(source = "timestamp", target = "timestamp")
	@Mapping(expression = "java( ex.getAppExCode().getHttpStatusCode().value() )", target = "httpStatusCode")
	@Mapping(expression = "java( ex.getAppExCode().getAppStatusCode() )", target = "errorCode")
	@Mapping(expression = "java( ex.getAppExCode().getMessage() )", target = "errorMessage")
	@Mapping(target = "validationErrors", ignore = true)
	@Mapping(target = "exMessage", ignore = true)
	@Mapping(target = "exTrackTrace", ignore = true)
	public abstract RespEntityErrorDTO toDTO( CustomException ex, String timestamp );

	@AfterMapping
	protected RespEntityErrorDTO afterMappingTDTO( CustomException ex, String timestamp,
																									@MappingTarget RespEntityErrorDTO errorDTO ) {
		if ( Objects.nonNull( ex.getValidationErrors() ) ) {
			errorDTO.setValidationErrors( ex.getValidationErrors() );
		}
		if ( Objects.nonNull( ex.getOriginalException() ) ) {
			errorDTO.setExMessage( ex.getOriginalException().getMessage() );
			errorDTO.setExTrackTrace( Arrays.toString( ex.getOriginalException().getStackTrace() ) );
		}

		return errorDTO;
	}
}
