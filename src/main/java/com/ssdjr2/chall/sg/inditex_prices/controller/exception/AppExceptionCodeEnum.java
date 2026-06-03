package com.ssdjr2.chall.sg.inditex_prices.controller.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AppExceptionCodeEnum {

	STATUS_40000( HttpStatus.BAD_REQUEST, 40000, "Bad Request" ),
	STATUS_40001( HttpStatus.BAD_REQUEST, 40001, "Bad Request: Validation errors" ),
	STATUS_40300( HttpStatus.FORBIDDEN, 40300, "Forbidden" ),
	STATUS_40400( HttpStatus.NOT_FOUND, 40400, "Not Found" ),
	STATUS_40401( HttpStatus.NOT_FOUND, 40401, "Price not Found" ),
	STATUS_40500( HttpStatus.METHOD_NOT_ALLOWED, 40500, "Method Not Allowed" ),
	STATUS_40600( HttpStatus.NOT_ACCEPTABLE, 40600, "Media Type Not Acceptable" ),
	STATUS_41500( HttpStatus.UNSUPPORTED_MEDIA_TYPE, 41500, "Media Type Not Unsupported" ),
	STATUS_50000( HttpStatus.INTERNAL_SERVER_ERROR, 50000, "Internal Server Error" ),
	STATUS_50300( HttpStatus.SERVICE_UNAVAILABLE, 50300, "Service Unavailable" );

	private final HttpStatus httpStatusCode;
	private final Integer appStatusCode;
	private final String message;

	AppExceptionCodeEnum(HttpStatus httpStatusCode, Integer appStatusCode, String message ) {
		this.httpStatusCode = httpStatusCode;
		this.appStatusCode = appStatusCode;
		this.message = message;
	}
}
