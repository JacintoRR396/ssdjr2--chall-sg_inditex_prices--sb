package com.ssdjr2.chall.sg.inditex_prices.price.service.application.exception;

import com.ssdjr2.chall.sg.inditex_prices.application.exception.AppExceptionCodeEnum;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.config.properties.LoggerProperties;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.error.RespEntityErrorDTO;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.handler.GlobalExceptionHandler;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.mapper.RespEntityErrorMapper;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.exception.PriceDomainException;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.exception.PriceNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(1)
@RestControllerAdvice
public class PriceGlobalExceptionHandler extends GlobalExceptionHandler {

	public PriceGlobalExceptionHandler(LoggerProperties loggerProperties, RespEntityErrorMapper respEntityErrorMapper) {
		super(loggerProperties, respEntityErrorMapper);
	}

	@ExceptionHandler(PriceDomainException.class)
	public ResponseEntity<RespEntityErrorDTO> handlePriceDomain(PriceDomainException ex ) {
		return super.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40002,null);
	}

	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<RespEntityErrorDTO> handlePriceNotFound(PriceNotFoundException ex ) {
		return super.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40401,null);
	}
}
